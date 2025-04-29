import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InvestmentInquiry } from '../models/investment-inquiry.model';
import { Observable } from 'rxjs';
import { APIURL } from '../constant/api_url';

@Injectable({
  providedIn: 'root'
})
export class InvestmentInquiryService {

  private APIurl:string = APIURL.APIurl;

  constructor(private http: HttpClient) { 
  }

  addInquiry(inquiry: InvestmentInquiry):Observable<InvestmentInquiry>{
    return this.http.post<InvestmentInquiry>(`${this.APIurl}/api/inquiries`, inquiry);
  }

  getAllInquries():Observable<InvestmentInquiry[]>{
    return this.http.get<InvestmentInquiry[]>(`${this.APIurl}/api/inquiries`)
  }

  getInquriesByUserId(userId: number):Observable<InvestmentInquiry>{
    return this.http.get<InvestmentInquiry>(`${this.APIurl}/api/inquiries/user/${userId}`);
  }

  updateInquiry(inquiryId: number, inquiry: InvestmentInquiry):Observable<InvestmentInquiry>{
    return this.http.put<InvestmentInquiry>(`${this.APIurl}/api/inquiries/${inquiryId}`, inquiry);
  }

  deleteInquiry(inquiryId: number):Observable<void>{
    return this.http.delete<void>(`${this.APIurl}/api/inquiries/${inquiryId}`);
  }
}