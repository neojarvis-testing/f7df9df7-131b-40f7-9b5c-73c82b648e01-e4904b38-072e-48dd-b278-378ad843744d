import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Feedback } from 'src/app/models/feedback.model';
import { Investment } from 'src/app/models/investment.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentService } from 'src/app/services/investment.service';
 
 
@Component({
  selector: 'app-user-add-feedback',
  templateUrl: './user-add-feedback.component.html',
  styleUrls: ['./user-add-feedback.component.css']
})
export class UserAddFeedbackComponent implements OnInit {
  feedbackForm: FormGroup;
  investments:Investment[]=[]
  categories: string[] = ['Portfolio', 'Advice', 'General'];
 
  constructor(private fb: FormBuilder, private feedbackService: FeedbackService, private investmentService:InvestmentService) {
    this.feedbackForm = this.fb.group({
      investment: ['', Validators.required],
      category: ['', Validators.required],
      date: ['', Validators.required],
      feedbackText: ['', Validators.required]
    });
  }
 
  ngOnInit(): void {
   this.loadInvestments()
  }
  loadInvestments(){
    this.investmentService.getAllInvestments().subscribe((data)=>{
      this.investments=data
    })
  }
  onSubmit(): void {
    if (this.feedbackForm.valid) {
      const feedback: Feedback = this.feedbackForm.value;
      this.feedbackService.sendFeedback(feedback).subscribe(response => {
        console.log('Feedback submitted successfully', response);
        this.feedbackForm.reset();
        alert('Feedback submitted successfully');
      }, error => {
        console.error('Error submitting feedback', error);
      });
    }
  }
 
  onCancel(): void {
    this.feedbackForm.reset();
  }
}