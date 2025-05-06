import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-admin-view-inquiry',
  templateUrl: './admin-view-inquiry.component.html',
  styleUrls: ['./admin-view-inquiry.component.css']
})
export class AdminViewInquiryComponent implements OnInit {

  inquiries: InvestmentInquiry[] = [];
  filteredInquiries: InvestmentInquiry[] = [];
  searchQuery: string = '';
  selectedStatus: string = '';
  selectedPriority: string = '';

  showDeletePopup = false;
  showResponsePopup = false;
  inquiryToDelete: number | null = null;
  inquiryToRespond: InvestmentInquiry | null = null;
  adminResponse: string = '';

  // Injecting services for handling inquiries and navigation
  constructor(private readonly inquiryService: InvestmentInquiryService, private readonly router: Router) {}

  ngOnInit(): void {
    this.loadInquiries();
  }

  // Fetch all inquiries from the backend service
  loadInquiries(): void {
    this.inquiryService.getAllInquries().subscribe((data) => {
      this.inquiries = data;
      this.filteredInquiries = data;
    });
  }

   // Apply filtering based on search query, selected status, and priority
  filterInquiries(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry => {
      return (
        (this.selectedStatus === '' || inquiry.status === this.selectedStatus) &&
        (this.selectedPriority === '' || inquiry.priority === this.selectedPriority) &&
        (this.searchQuery === '' || inquiry.message.toLowerCase().includes(this.searchQuery.toLowerCase()))
      );
    });
  }

  // Prepare for deleting an inquiry and show the delete confirmation popup
  confirmDeleteInquiry(inquiryId: number): void {
    this.inquiryToDelete = inquiryId;
    this.showDeletePopup = true;
    console.log(inquiryId);
    this.router.navigate(['/admin/view-inquiries']);
  }

  // Proceed with inquiry deletion after confirmation
  onDelete(): void {
    if (this.inquiryToDelete !== null) {
      this.inquiryService.deleteInquiry(this.inquiryToDelete).subscribe(() => {
        this.loadInquiries();
        this.closeDeletePopup();
        this.router.navigate(['/admin/view-inquiries']);
      }, error => {
        console.error('Error deleting inquiry:', error);
        this.closeDeletePopup(); // Ensure modal closes even if there's an error
      });
    }
  }

   // Close the delete confirmation popup
  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.inquiryToDelete = null;
    this.loadInquiries();
  }

  // Open response submission popup for a specific inquiry
  openResponsePopup(inquiry: InvestmentInquiry): void {
    this.inquiryToRespond = inquiry;
    this.adminResponse = inquiry.adminResponse || '';
    this.showResponsePopup = true; // Show response popup
  }

   // Close the response submission popup without saving changes
  closeResponsePopup(): void {
    this.showResponsePopup = false;
    this.inquiryToRespond = null;
    this.adminResponse = '';
  }

  // Submit admin response and update inquiry status
  submitAdminResponse(): void {
    if (this.inquiryToRespond) {
      this.inquiryToRespond.adminResponse = this.adminResponse;
      this.inquiryToRespond.status = 'Resolved';
      this.inquiryService.updateInquiry(this.inquiryToRespond.inquiryId!, this.inquiryToRespond).subscribe(() => {
        this.loadInquiries();
        this.closeResponsePopup();
      });
    }
  }
}
