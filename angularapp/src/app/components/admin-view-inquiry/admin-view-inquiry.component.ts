import { Component, OnInit } from '@angular/core';
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

  constructor(private inquiryService: InvestmentInquiryService) {}

  ngOnInit(): void {
    this.loadInquiries();
  }

  loadInquiries(): void {
    this.inquiryService.getAllInquries().subscribe(data => {
      this.inquiries = data;
      this.filteredInquiries = [...this.inquiries]; // Initialize filter results
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

  deleteInquiry(inquiryId: number): void {
    if (confirm("Are you sure you want to delete this inquiry?")) {
      this.inquiryService.deleteInquiry(inquiryId).subscribe(() => {
        this.loadInquiries();
      });
    }
  }

  addAdminResponse(inquiry: InvestmentInquiry): void {
    const response = prompt("Enter admin response:");
    if (response) {
      inquiry.adminResponse = response;
      inquiry.status = "Resolved";
      this.inquiryService.updateInquiry(inquiry.inquiryId!, inquiry).subscribe(() => {
        this.loadInquiries();
      });
    }
  }

}
