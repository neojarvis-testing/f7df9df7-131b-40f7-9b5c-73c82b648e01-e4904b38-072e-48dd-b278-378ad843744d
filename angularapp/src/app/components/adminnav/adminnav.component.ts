import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
  adminName: string | null = ''
  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
      this.adminName = localStorage.getItem('username');
  }

  logout(){
    if(confirm("Are you sure you want to logout?")){
      this.authService.loggedOut();
      this.router.navigate(['/login']);
    }
  }
}
