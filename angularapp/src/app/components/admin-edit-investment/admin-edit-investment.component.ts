import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';

@Component({
  selector: 'app-admin-edit-investment',
  templateUrl: './admin-edit-investment.component.html',
  styleUrls: ['./admin-edit-investment.component.css']
})
export class AdminEditInvestmentComponent implements OnInit {
  investmentForm: FormGroup;
  investmentId: number;
  showSuccessPopup = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly investmentService: InvestmentService,
    private readonly activatedRoute: ActivatedRoute
  ) {
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
  investData:Investment={
        name: '',
        description: '',
        type: '',
        purchasePrice: 0,
        currentPrice: 0,
        quantity: 0,
        purchaseDate: '',
        status: ''
      }

  ngOnInit(): void {
    this.investmentId = +this.activatedRoute.snapshot.params['investmentId'];
    this.loadInvestmentDetails();
  }

  loadInvestmentDetails(): void {
    this.investmentService.getInvestmentById(this.investmentId).subscribe({
      next: (investment: Investment) => {
        this.investmentForm.patchValue(investment);
      },
      error: (error) => {
        console.error('Error fetching investment:', error);
        alert('An error occurred while fetching the investment details. Please try again.');
      }
    });
  }

  onSubmit(): void {
    if (this.investmentForm.valid) {
      this.showSuccessPopup = true;
      this.investmentService.updateInvestment(this.investmentId, this.investmentForm.value).subscribe({
        next: (response) => {
          console.log('Investment updated:', response);
        },
        error: (err) => {
          console.error('Failed to update investment:', err);
          alert('An error occurred while updating the investment. Please try again.');
        },
      });
    }
  }

  closePopup(): void {
    this.showSuccessPopup = false;
    this.router.navigate(['/admin/view-investment']);
  }
}