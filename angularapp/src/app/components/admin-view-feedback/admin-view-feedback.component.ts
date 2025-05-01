import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

declare var bootstrap: any;

@Component({
  selector: 'app-admin-view-feedback',
  templateUrl: './admin-view-feedback.component.html'
})
export class AdminViewFeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  filteredFeedbacks: Feedback[] = [];
  categories: string[] = ['Portfolio', 'Advice', 'General']; 
  selectedCategory: string = '';

  selectedUser: any = null;
  selectedInvestment: any = null;

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  loadFeedbacks(): void {
    this.feedbackService.getFeedbacks().subscribe(response => {
      this.feedbacks = response;
      this.filteredFeedbacks = response;
    }, error => {
      console.error('Error loading feedbacks', error);
    });
  }

  filterFeedbacks(): void {
    this.filteredFeedbacks = this.selectedCategory
      ? this.feedbacks.filter(fb => fb.category === this.selectedCategory)
      : this.feedbacks;
  }

  showProfile(user: any): void {
    this.selectedUser = user;
    const modal = new bootstrap.Modal(document.getElementById('userModal')!);
    modal.show();
  }

  viewInvestmentDetails(investment: any): void {
    this.selectedInvestment = investment;
    const modal = new bootstrap.Modal(document.getElementById('investmentModal')!);
    modal.show();
  }
}
