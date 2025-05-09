import { Injectable } from '@angular/core';
import { APIURL } from '../constant/api_url';
import { HttpClient } from '@angular/common/http';
import { Investment } from '../models/investment.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InvestmentService {
  private readonly APIurl:string = APIURL.APIurl;
  constructor(private readonly http:HttpClient) { 
  }

  getAllInvestments():Observable<Investment[]>{
    return this.http.get<Investment[]>(`${this.APIurl}/api/investments`)
  }

  getInvestmentById(investmentId:number):Observable<Investment>{
    return this.http.get<Investment>(`${this.APIurl}/api/investments/${investmentId}`)
  }
 
  addInvestment(investment:Investment):Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/investments`,investment)
  }

  updateInvestment(investmentId:number,investment:Investment):Observable<Investment>{
    return this.http.put<Investment>(`${this.APIurl}/api/investments/${investmentId}`,investment)
  }

  deleteInvestment(investmentId:number):Observable<void>{
    return this.http.delete<void>(`${this.APIurl}/api/investments/${investmentId}`)
  }
}
