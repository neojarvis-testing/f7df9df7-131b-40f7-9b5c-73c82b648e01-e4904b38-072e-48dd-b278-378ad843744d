import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';


@Component({
  selector: 'app-admin-view-feedback',
  templateUrl: './admin-view-feedback.component.html',
  styleUrls: ['./admin-view-feedback.component.css']
})
export class AdminViewFeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  filteredFeedbacks: Feedback[] = [];
  categories: string[] = ['Portfolio', 'Advice', 'General']; 
  selectedCategory: string = '';

  constructor(private feedbackService: FeedbackService) { }

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
    if (this.selectedCategory) {
      this.filteredFeedbacks = this.feedbacks.filter(feedback => feedback.category === this.selectedCategory);
    } else {
      this.filteredFeedbacks = this.feedbacks;
    }
  }

  showProfile(user: any): void {
    // Logic to show user profile in a popup
    alert(`User Info: ${JSON.stringify(user)}`);
  }

  viewInvestmentDetails(investment: any): void {
    // Logic to show investment details in a popup
    alert(`Investment Details: ${JSON.stringify(investment)}`);
  }
}
