import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Invest Track';
  // decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
  // userRole = this.decryptedLogin.userRole; // Access property
  userRole = localStorage.getItem('userRole');
  ngOnInit(): void {
  }
}
