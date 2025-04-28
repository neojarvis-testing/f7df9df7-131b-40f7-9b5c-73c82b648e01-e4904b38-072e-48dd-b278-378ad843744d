import { Injectable } from '@angular/core';
import { APIURL } from '../constant/api_url';
import { HttpClient } from '@angular/common/http';
import { Investment } from '../models/investment.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InvestmentService {
private apiUrl:string;
  constructor(private http:HttpClient, private apiBaseUrl:APIURL) { 
    this.apiUrl = this.apiBaseUrl.APIurl;
  }

  getAllInvestments():Observable<Investment[]>{
    return this.http.get<Investment[]>(`${this.apiUrl}/investments`)
  }

  getInvestmentById(investmentId:number):Observable<Investment>{
    return this.http.get<Investment>(`${this.apiUrl}/investments/${investmentId}`)
  }
 
  addInvestment(investment:Investment):Observable<Investment>{
    return this.http.post<Investment>(`${this.apiUrl}/investments`,investment)
  }

  updateInvestment(investmentId:number,investment:Investment):Observable<Investment>{
    return this.http.put<Investment>(`${this.apiUrl}/investments/${investmentId}`,investment)
  }

  deleteInvestment(investmentId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/investments/${investmentId}`)
  }
}
