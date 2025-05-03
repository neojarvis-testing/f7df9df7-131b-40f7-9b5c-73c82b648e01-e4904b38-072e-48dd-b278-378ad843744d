import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
})
export class HomePageComponent {

  constructor(public authService: AuthService, private readonly router: Router) { }

  logout(){
    if(confirm("Are you sure you want to logout?")){
      this.authService.loggedOut();
      this.router.navigate(['/login']);
    }
  }
}
