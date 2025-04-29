import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: Login = {
    email:'',
    password:''
  };
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  loginUser(){
    this.authService.login(this.login).subscribe();
    this.login = null;
    this.router.navigate(["/"]);
  }
}