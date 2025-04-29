import { Component , OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-admin-add-investment',
  templateUrl: './admin-add-investment.component.html',
  styleUrls: ['./admin-add-investment.component.css']
})
export class AdminAddInvestmentComponent {
  ngOnInit(): void {
  }
  investmentForm: FormGroup;
  showSuccessPopup = false;
  constructor(private fb: FormBuilder) {
    this.investmentForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      purcahsePrice: ['', [Validators.required, Validators.min(0)]],
      currentPrice: ['', Validators.required],
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      status: ['', Validators.required],
    });
  }
  onSubmit(): void {
    if (this.investmentForm.valid) {
      // Normally you'd call a service here
      this.showSuccessPopup = true;
    }
  }
  closePopup(): void {
    this.showSuccessPopup = false;
    this.investmentForm.reset();
  }

} 