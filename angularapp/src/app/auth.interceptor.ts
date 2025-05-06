import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor
} from '@angular/common/http';
import { AuthService } from './services/auth.service';
 
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
 
  constructor(private readonly authService: AuthService) { }
 
  intercept(request: HttpRequest<unknown>, next: HttpHandler) {
    if(request.url.includes("/login") || request.url.includes("/register")){
      return next.handle(request);
    }
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    let token = decryptedLogin.token; // Access property

    //let token = localStorage.getItem('token');
    console.log(token)
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
   
    return next.handle(request);
    }
    return next.handle(request)
  }
}