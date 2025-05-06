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
  constructor(private readonly http: HttpClient) { }

  register(user: User): Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/register`, user);
  }

  login(login: Login): Observable<any>{
    return this.http.post<any>(`${this.APIurl}/api/login`, login);
  }
  isAdmin():boolean{
    let data = localStorage.getItem('encryptedLogin')
    if(data==null)
       return false;
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    //console.log(decryptedLogin);
    let role = decryptedLogin.userRole; // Access property
    //let role=localStorage.getItem('userRole')
    return role=='Admin'
  }
 
  isUser():boolean{
    let data = localStorage.getItem('encryptedLogin')
    if(data==null)
       return false;
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    let role = decryptedLogin.userRole; // Access property
    // let role=localStorage.getItem('userRole')
    return role=='User'
  }
 
  isLoggedUser():boolean{
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    let role = decryptedLogin.userRole; // Access property
    //let role=localStorage.getItem('userRole')
    return role!=null
  }
 
  loggedOut():void{
    // //localStorage.removeItem('encryptedLogin')
    // localStorage.removeItem('userId')
    // localStorage.removeItem('userRole')
    // localStorage.clear()
   // localStorage.removeItem('userId');
    localStorage.removeItem('userRole');
    localStorage.removeItem('encryptedLogin'); // Ensure login data is removed correctly
  }
}