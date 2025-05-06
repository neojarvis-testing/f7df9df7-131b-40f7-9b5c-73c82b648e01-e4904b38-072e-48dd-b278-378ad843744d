import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
  showLogoutModal: boolean = false; // Controls modal visibility
  adminName: string | null = ''; // Stores admin name

  constructor(public authService: AuthService, private readonly router: Router) {}

  ngOnInit(): void {
    let decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
    this.adminName = decryptedLogin.username; // Access property

    //this.adminName = localStorage.getItem('username'); // Fetch admin name from localStorage
  }

  // Opens the modal and adds blur effect
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
    this.closeModal(); // Close modal after logout
    this.router.navigate(['/login']);
  }
}