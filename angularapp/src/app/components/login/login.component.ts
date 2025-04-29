import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login = {email: '', password: ''};
  
  user:User={
    email: '',
    password: '',
    username: '',
    mobileNumber: '',
    userRole: ''
  };
  errorMessage:string=''

  constructor(private service:AuthService, private router:Router) { }

  ngOnInit(): void {
    
  }

  loginUser(){
    console.log(this.login)
     this.service.login(this.login).subscribe((data)=>{
      this.user = data;
      localStorage.setItem('userId', data.userId+"")
      localStorage.setItem('username', data.username)
      localStorage.setItem('token', data.token)
      localStorage.setItem('userRole', data.userRole)
      if(this.user.userRole==='Admin')
        this.router.navigate(['/admin'])
      else if(this.user.userRole==='User')
        this.router.navigate(['/user'])
    },
    (error)=>{
      console.log(error)
      this.errorMessage='Incorrect Email or password. Try Again!!'
    });
  }
}