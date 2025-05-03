import { Component } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-investment',
  templateUrl: './admin-add-investment.component.html',
  styleUrls: ['./admin-add-investment.component.css']
})
export class AdminAddInvestmentComponent {
  showSuccessPopup = false;
  investmentForm: FormGroup;

  constructor(private readonly fb: FormBuilder, private readonly investmentService: InvestmentService, private readonly router:Router) {
    this.investmentForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      purchasePrice: ['', [Validators.required, Validators.min(0)]],
      currentPrice: ['', Validators.required],
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      status: ['', Validators.required],
    });
  }
}
