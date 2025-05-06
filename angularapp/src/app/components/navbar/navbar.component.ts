import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userRole: string = '';
  constructor(public authService: AuthService, private readonly router: Router) { 
    // let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    // this.userRole = decryptedLogin.userRole; // Access property

    this.userRole = localStorage.getItem('userRole');
  }

  ngOnInit(): void {
    // let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    // this.userRole = decryptedLogin.userRole; // Access property
    this.userRole = localStorage.getItem('userRole');
  }
}