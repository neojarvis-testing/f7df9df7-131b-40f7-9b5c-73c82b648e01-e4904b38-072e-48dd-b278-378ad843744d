import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { APIURL } from '../constant/api_url';
import { Login } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  APIurl = APIURL.APIurl;
  constructor(private http: HttpClient) { }

  register(user: User): Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/register`, user);
  }

  login(login: Login): Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/login`, login);
  }
}
