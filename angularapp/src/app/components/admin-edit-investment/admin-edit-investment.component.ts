// // import { Component, OnInit } from '@angular/core';
// // import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// // import { ActivatedRoute, Router } from '@angular/router';
// // import { InvestmentService } from 'src/app/services/investment.service';

// // @Component({
// //   selector: 'app-admin-edit-investment',
// //   templateUrl: './admin-edit-investment.component.html',
// //   styleUrls: ['./admin-edit-investment.component.css']
// // })
// // export class AdminEditInvestmentComponent implements OnInit {
// //   investmentForm: FormGroup;
// //   investmentId: number;
// //   showSuccessPopup = false;

// //   constructor(private fb: FormBuilder,private route: ActivatedRoute,private router: Router,private investmentService: InvestmentService){
// //    this.investmentForm = this.fb.group({
// //       name: ['', Validators.required],
// //       description: ['', Validators.required],
// //       type: ['', Validators.required],
// //       purchasePrice: ['', Validators.required],
// //       currentPrice: ['', Validators.required],
// //       quantity: ['', Validators.required],
// //       purchaseDate: ['', Validators.required],
// //       status: ['', Validators.required]
// //     });
// //   }

// //   ngOnInit(): void {
// //     this.investmentId = this.route.snapshot.params['id'];
// //     if (this.investmentId) {
// //       this.investmentService.getInvestmentById(this.investmentId).subscribe(data => {
// //         this.investmentForm.patchValue(data);
// //       });
// //     }
// //   }

// //   onSubmit(): void {
// //     if (this.investmentForm.valid) {
// //       this.investmentService.updateInvestment(this.investmentId, this.investmentForm.value).subscribe(() => {
// //         this.showSuccessPopup = true;
// //         setTimeout(() => {
// //           this.router.navigate(['/view-investments']);
// //         }, 2000);
// //       });
// //     }
// //   }

// //   closePopup(): void {
// //     this.showSuccessPopup = false;
// //   }
// // }
// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { InvestmentService } from '../../services/investment.service';
// import { Investment } from '../../models/investment.model';

// @Component({
//   selector: 'app-admin-edit-investment',
//   templateUrl: './admin-edit-investment.component.html',
//   styleUrls: ['./admin-edit-investment.component.css']
// })
// export class AdminEditInvestmentComponent implements OnInit {
//   investmentForm: FormGroup;
//   investmentId: number;
//   showSuccessPopup = false;

//   constructor(
//     private fb: FormBuilder,
//     private route: ActivatedRoute,
//     private router: Router,
//     private investmentService: InvestmentService
//   ) {
//     this.investmentForm = this.fb.group({
//       name: ['', Validators.required],
//       description: ['', Validators.required],
//       type: ['', Validators.required],
//       purchasePrice: ['', [Validators.required, Validators.min(0)]],
//       currentPrice: ['', Validators.required],
//       quantity: ['', Validators.required],
//       purchaseDate: ['', Validators.required],
//       status: ['', Validators.required],
//     });
//   }
//   investData:Investment={
//     name: '',
//     description: '',
//     type: '',
//     purchasePrice: 0,
//     currentPrice: 0,
//     quantity: 0,
//     purchaseDate: '',
//     status: ''
//   }

//   ngOnInit(): void {
//     this.route.paramMap.subscribe(params => {
//       this.investmentId = +params.get('id')!;
//       this.loadInvestmentDetails();
//     });
  
//     this.investmentForm.valueChanges.subscribe(values => {
//       this.investData = { ...this.investData, ...values };
//     });
//   }

//   loadInvestmentDetails(): void {
//     this.investmentService.getInvestmentById(this.investmentId).subscribe({
//       next: (investment: Investment) => {
//         this.investData = investment;
//         this.investmentForm.setValue({
//           name: investment.name,
//           description: investment.description,
//           type: investment.type,
//           purchasePrice: investment.purchasePrice,
//           currentPrice: investment.currentPrice,
//           quantity: investment.quantity,
//           purchaseDate: investment.purchaseDate,
//           status: investment.status,
//         });
//       },
//       error: (error) => {
//         console.error('Error fetching investment:', error);
//         alert('An error occurred while fetching the investment details. Please try again.');
//       }
//     });
//   }
  

//   onSubmit(): void {
//     if (this.investmentForm.valid) {
//       // const updatedInvestment: Investment = this.investmentForm.value;
//       this.showSuccessPopup = true;
//       this.investmentService.updateInvestment(this.investmentId, this.investData).subscribe({
//         next: (response) => {
//           console.log('Investment updated:', response);
//           this.router.navigate(['/admin/view-investment']);
//         },
//         error: (err) => {
//           console.error('Failed to update investment:', err);
//           alert('An error occurred while updating the investment. Please try again.');
//         },
//       });
//     }
//   }
//   closePopup(): void {
//         this.showSuccessPopup = false;
//       }
// }


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
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private investmentService: InvestmentService,
    private activatedRoute: ActivatedRoute
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

