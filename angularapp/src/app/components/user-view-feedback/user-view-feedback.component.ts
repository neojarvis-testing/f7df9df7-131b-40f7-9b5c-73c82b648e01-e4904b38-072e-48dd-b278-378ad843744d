import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';


@Component({
  selector: 'app-user-view-feedback',
  templateUrl: './user-view-feedback.component.html',
  styleUrls: ['./user-view-feedback.component.css']
})
export class UserViewFeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  loadFeedbacks(): void {
    this.feedbackService.getFeedbacks().subscribe(response => {
      this.feedbacks = response;
    }, error => {
      console.error('Error loading feedbacks', error);
    });
  }
}
