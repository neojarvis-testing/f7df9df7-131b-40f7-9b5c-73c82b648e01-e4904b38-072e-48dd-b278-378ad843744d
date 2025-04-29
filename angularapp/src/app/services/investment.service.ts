import { Injectable } from '@angular/core';
import { APIURL } from '../constant/api_url';
import { HttpClient } from '@angular/common/http';
import { Investment } from '../models/investment.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InvestmentService {
  private APIurl:string = APIURL.APIurl;
  constructor(private http:HttpClient) { 
  }

  getAllInvestments():Observable<Investment[]>{
    return this.http.get<Investment[]>(`${this.APIurl}/investments`)
  }

  getInvestmentById(investmentId:number):Observable<Investment>{
    return this.http.get<Investment>(`${this.APIurl}/investments/${investmentId}`)
  }
 
  addInvestment(investment:Investment):Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/investments`,investment)
  }

  updateInvestment(investmentId:number,investment:Investment):Observable<Investment>{
    return this.http.put<Investment>(`${this.APIurl}/investments/${investmentId}`,investment)
  }

  deleteInvestment(investmentId:number):Observable<void>{
    return this.http.delete<void>(`${this.APIurl}/investments/${investmentId}`)
  }
}
