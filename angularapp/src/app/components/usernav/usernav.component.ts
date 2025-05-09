import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {
  username: string | null = '';
  showLogoutModal: boolean = false;

  constructor(private readonly authService: AuthService, private readonly router: Router) {}

  ngOnInit(): void {
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    this.username = decryptedLogin.username; // Access property
    //this.username = localStorage.getItem('username');
  }

  logout(): void {
    this.showLogoutModal = true;
    document.body.classList.add('modal-open'); // Apply blur
  }

  // Closes the modal and removes blur effect
  closeModal(): void {
    this.showLogoutModal = false;
    document.body.classList.remove('modal-open'); // Remove blur
  }

  // Confirms logout and navigates to login
  confirmLogout(): void {
    this.authService.loggedOut();
    this.router.navigate(['/login']);
    this.closeModal(); // Close modal after logout
  }
}