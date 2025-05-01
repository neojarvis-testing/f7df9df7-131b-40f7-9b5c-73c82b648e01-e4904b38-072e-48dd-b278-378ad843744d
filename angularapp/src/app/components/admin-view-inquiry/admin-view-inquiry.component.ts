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

  constructor(private inquiryService: InvestmentInquiryService, private router: Router) {}

  ngOnInit(): void {
    this.loadInquiries();
  }

  loadInquiries(): void {
    this.inquiryService.getAllInquries().subscribe((data) => {
      this.inquiries = data;
      this.filteredInquiries = data;
    });
  }

  filterInquiries(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry => {
      return (
        (this.selectedStatus === '' || inquiry.status === this.selectedStatus) &&
        (this.selectedPriority === '' || inquiry.priority === this.selectedPriority) &&
        (this.searchQuery === '' || inquiry.message.toLowerCase().includes(this.searchQuery.toLowerCase()))
      );
    });
  }

  confirmDeleteInquiry(inquiryId: number): void {
    this.inquiryToDelete = inquiryId;
    this.showDeletePopup = true;
    console.log(inquiryId);
    this.router.navigate(['/admin/view-inquiries']);
  }

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

  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.inquiryToDelete = null;
    this.loadInquiries();
  }

  openResponsePopup(inquiry: InvestmentInquiry): void {
    this.inquiryToRespond = inquiry;
    this.adminResponse = inquiry.adminResponse || '';
    this.showResponsePopup = true;
  }

  closeResponsePopup(): void {
    this.showResponsePopup = false;
    this.inquiryToRespond = null;
    this.adminResponse = '';
  }

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
