import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',

})
export class AppComponent {
  title = 'Invest Track';
  userRole = localStorage.getItem('userRole');
 
}
