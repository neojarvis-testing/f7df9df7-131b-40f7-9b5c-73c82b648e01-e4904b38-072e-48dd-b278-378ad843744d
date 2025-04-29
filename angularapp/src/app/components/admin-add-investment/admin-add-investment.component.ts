import { Component, OnInit } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-add-investment',
  templateUrl: './admin-add-investment.component.html',
  styleUrls: ['./admin-add-investment.component.css']
})
export class AdminAddInvestmentComponent implements OnInit {
  ngOnInit(): void {
  }
  showSuccessPopup = false;
  investmentForm: FormGroup;

  constructor(private fb: FormBuilder, private investmentService: InvestmentService) {
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

  onSubmit(): void {
    if (this.investmentForm.valid) {
      const newInvestment: Investment = this.investmentForm.value;
      this.investmentService.addInvestment(newInvestment).subscribe({
        next: (response) => {
          console.log('Investment added:', response);
          this.showSuccessPopup = true;
        },
        error: (err) => {
          console.error('Failed to add investment:', err);
          alert('An error occurred while adding the investment. Please try again.');
        },
      });
    }
  }
  closePopup(): void {
    this.showSuccessPopup = false;
    this.investmentForm.reset();
  }
}
