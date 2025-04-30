import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { Investment } from 'src/app/models/investment.model';
import { User } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentService } from 'src/app/services/investment.service';
 
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
  investmentId:any
  investments: Investment[] = [];
  categories: string[] = ['Portfolio', 'Advice', 'General'];
 
  // todayDate: string = new Date().toISOString().split('T')[0];
 
 
  constructor(private feedbackService: FeedbackService, private investmentService: InvestmentService, private router:Router) {}
 
  ngOnInit(): void {
    this.loadInvestments();
  }
 
  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data) => {
      this.investments = data;
    });
  }
 
  onSubmit(): void {
    console.log(this.investmentId)
    this.feedback.investment={
      investmentId:this.investmentId
    }
    let userId = localStorage.getItem('userId');
      this.feedback.user={
        userId: Number(userId)
      }
    console.log(this.feedback)
    if (this.feedback.feedbackText && this.feedback.date && this.feedback.category && this.feedback.investment) {
      // Ensure investment is sent as an object, not a string
      //this.feedback.investment = this.investments.find(inv => inv.investmentId === this.feedback.investment.investmentId) || {} as Investment;
     
      this.feedbackService.sendFeedback(this.feedback).subscribe(
        (response) => {
          console.log('Feedback submitted successfully', response);
          alert('Feedback submitted successfully');
          this.resetForm();
          this.router.navigate(['user/view-feedback']);
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
}