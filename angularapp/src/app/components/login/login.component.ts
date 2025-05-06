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
  encryptedLogin: any;
  
  user:User={
    email: '',
    password: '',
    username: '',
    mobileNumber: '',
    userRole: ''
  };
  errorMessage:string=''

  constructor(private readonly service:AuthService, private readonly router:Router) { }

  ngOnInit(): void {}

  loginUser(){
    console.log(this.login)
     this.service.login(this.login).subscribe((data)=>{
      this.user = data;
      //this.encryptedLogin = btoa(JSON.stringify(data));
      localStorage.setItem('encryptedLogin', btoa(JSON.stringify(data)));      //console.log(atob(localStorage.getItem('encryptedLogin')));

      // localStorage.setItem('userId', data.userId+"")
      // localStorage.setItem('username', data.username)
      // localStorage.setItem('token', data.token)
      localStorage.setItem('userRole', data.userRole)
      this.router.navigate(['/home'])
    },
    (error)=>{
      console.log(error)
      this.errorMessage='Incorrect Email or password. Try Again!!'
    });
      const decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin')))
      console.log(decryptedLogin);
      let userRole1 = decryptedLogin.userRole;
      console.log(userRole1);
  }
}