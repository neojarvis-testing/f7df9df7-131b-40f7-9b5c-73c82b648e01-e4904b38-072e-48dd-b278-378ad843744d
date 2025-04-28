import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIURL } from '../constant/api_url';
import { Feedback } from '../models/feedback.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private apiUrl:string;
  constructor(private http:HttpClient, private apiBaseUrl:APIURL) {
    this.apiUrl = this.apiBaseUrl.APIurl
  }
  
  sendFeedback(feedback:Feedback):Observable<Feedback>{
    return this.http.post<Feedback>(`${this.apiUrl}/feedback`,feedback);
  }
  getAllFeedbacksByUserId(userId:number):Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback/user/${userId}`)
  }
  deleteFeedback(feedbackId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/feedback/${feedbackId}`)
  }
  getFeedbacks():Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback`)
  }
}
