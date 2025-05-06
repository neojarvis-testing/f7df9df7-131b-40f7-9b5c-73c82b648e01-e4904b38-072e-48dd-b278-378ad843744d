import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { Investment } from 'src/app/models/investment.model';
import { User } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentService } from 'src/app/services/investment.service';
 
declare var bootstrap: any;
 
@Component({
  selector: 'app-user-add-feedback',
  templateUrl: './user-add-feedback.component.html',
  styleUrls: ['./user-add-feedback.component.css']
})
export class UserAddFeedbackComponent implements OnInit {
  feedback: Feedback = {
    feedbackText: '',
    date: new Date(),
    category: ''
  };
  investmentId: any;
  investments: Investment[] = [];
  categories: string[] = ['Portfolio', 'Advice', 'General'];
 
  constructor(
    private readonly feedbackService: FeedbackService,
    private readonly investmentService: InvestmentService,
    private readonly router: Router
  ) {}
 
  ngOnInit(): void {
    this.loadInvestments();
  }
 
  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data) => {
      this.investments = data;
    });
  }
 
  onSubmit(): void {
    this.feedback.investment = {
      investmentId: this.investmentId
    };
    let userId = localStorage.getItem('userId');
    this.feedback.user = {
      userId: Number(userId)
    };
 
    if (this.feedback.feedbackText && this.feedback.date && this.feedback.category && this.feedback.investment) {
      this.feedbackService.sendFeedback(this.feedback).subscribe(
        (response) => {
          console.log('Feedback submitted successfully', response);
          this.resetForm();
 
          // Show Bootstrap modal
          const modalElement = document.getElementById('successModal');
          const modal = new bootstrap.Modal(modalElement);
          modal.show();
        },
        (error) => {
          console.error('Error submitting feedback', error);
        }
      );
    }
  }
 
  resetForm(): void {
    this.feedback = {
      feedbackText: '',
      date: new Date(),
      investment: {} as Investment,
      user: {} as User,
      category: ''
    };
  }
  
  formatDate() {
    const inputElement = document.getElementById("date") as HTMLInputElement;
    const rawDate = inputElement.value;
  
    // Extract just the date portion using regex
    const match = rawDate.match(/(\d{4}-\d{2}-\d{2})/); // Matches YYYY-MM-DD format
    if (match) {
      inputElement.value = match[0]; // Set only the date
    }
  }
  navigateToFeedback(): void {
    this.router.navigate(['user/view-feedback']);
  }
}