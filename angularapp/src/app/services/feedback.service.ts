import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIURL } from '../constant/api_url';
import { Feedback } from '../models/feedback.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private readonly APIurl:string = APIURL.APIurl;
  constructor(private readonly http:HttpClient) {
  }
  
  sendFeedback(feedback:Feedback):Observable<Feedback>{
    return this.http.post<Feedback>(`${this.APIurl}/api/feedback`,feedback);
  }
  getAllFeedbacksByUserId(userId:number):Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.APIurl}/api/feedback/user/${userId}`)
  }
  deleteFeedback(feedbackId:number):Observable<void>{
    return this.http.delete<void>(`${this.APIurl}/api/feedback/${feedbackId}`)
  }
  getFeedbacks():Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.APIurl}/api/feedback`)
  }
}
