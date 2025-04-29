import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-edit-investment',
  templateUrl: './admin-edit-investment.component.html',
  styleUrls: ['./admin-edit-investment.component.css']
})
export class AdminEditInvestmentComponent implements OnInit {
  investmentForm: FormGroup;
  investmentId: number;
  showSuccessPopup = false;

  constructor(private fb: FormBuilder,private route: ActivatedRoute,private router: Router,private investmentService: InvestmentService){
   this.investmentForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      purchasePrice: ['', Validators.required],
      currentPrice: ['', Validators.required],
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.investmentId = this.route.snapshot.params['id'];
    if (this.investmentId) {
      this.investmentService.getInvestmentById(this.investmentId).subscribe(data => {
        this.investmentForm.patchValue(data);
      });
    }
  }

  onSubmit(): void {
    if (this.investmentForm.valid) {
      this.investmentService.updateInvestment(this.investmentId, this.investmentForm.value).subscribe(() => {
        this.showSuccessPopup = true;
        setTimeout(() => {
          this.router.navigate(['/view-investments']);
        }, 2000);
      });
    }
  }

  closePopup(): void {
    this.showSuccessPopup = false;
  }
}
