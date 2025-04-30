import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-view-investment',
  templateUrl: './user-view-investment.component.html',
  styleUrls: ['./user-view-investment.component.css']
})
export class UserViewInvestmentComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  addInquiry(investmentId: number){
    this.router.navigate(['/user/add-inquiry', investmentId]);
  }
}
