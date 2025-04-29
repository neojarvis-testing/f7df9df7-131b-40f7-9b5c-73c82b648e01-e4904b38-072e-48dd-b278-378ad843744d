import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';


@Component({
  selector: 'app-admin-view-feedback',
  templateUrl: './admin-view-feedback.component.html',
  styleUrls: ['./admin-view-feedback.component.css']
})
export class AdminViewFeedbackComponent implements OnInit {

  feedbackList: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.loadFeedback();
  }

  loadFeedback(): void {
    this.feedbackService.getFeedbacks().subscribe(
      (data: Feedback[]) => {
        this.feedbackList = data;
      },
      error => {
        console.error('Error fetching feedback', error);
      }
    );
  }
}