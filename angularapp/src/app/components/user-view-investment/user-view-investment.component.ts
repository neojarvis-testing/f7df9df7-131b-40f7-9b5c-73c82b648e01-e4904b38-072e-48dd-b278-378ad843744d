import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
 
@Component({
  selector: 'app-user-view-investment',
  templateUrl: './user-view-investment.component.html',
  styleUrls: ['./user-view-investment.component.css']
})
export class UserViewInvestmentComponent implements OnInit {
  investments: Investment[] = [];
 
  constructor(private investmentService: InvestmentService,private activatedRoute:ActivatedRoute,private router: Router) {}
 
  ngOnInit(): void {
    this.loadInvestments();
  }
loadInvestments(): void {
  this.investmentService.getAllInvestments().subscribe({
    next: (data) => {
      this.investments = data;
    },
    error: (err) => {
      console.error('Failed to load investments:', err);
    }
  });
}
 
 
inquire(investmentId: number): void {
   this.router.navigate(['/user/add-inquiry', investmentId]); 
} 
}