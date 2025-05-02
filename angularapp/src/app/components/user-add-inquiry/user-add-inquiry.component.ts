import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { InvestmentInquiry } from "src/app/models/investment-inquiry.model";
import { Investment } from "src/app/models/investment.model";
import { InvestmentInquiryService } from "src/app/services/investment-inquiry.service";
import { InvestmentService } from "src/app/services/investment.service";

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})

export class UserAddInquiryComponent implements OnInit {
  investmentId: any;
  showSuccessPopup = false;

  inquiry: InvestmentInquiry = {
    message: '',
    status: 'Pending',
    inquiryDate: new Date(),
    responseDate: new Date(),
    adminResponse: 'No response yet',
    priority: '',
    contactDetails: ''
  };

  investment: Investment = {
    name: '',
    description: "",
    type: "",
    purchasePrice: 0,
    currentPrice: 0,
    quantity: 0,
    purchaseDate: "",
    status: ""
  };

  constructor(private readonly investmentinquiryService: InvestmentInquiryService, private readonly investmentService: InvestmentService, 
    private readonly router: Router, private readonly activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.investmentId = +this.activatedRoute.snapshot.params['investmentId'];
    this.investmentService.getInvestmentById(this.investmentId).subscribe((data) => {
      this.investment = data;
    });
  }

  addInquiry() {
    let userId = localStorage.getItem('userId');
    this.inquiry.user = {
      userId: Number(userId)
    };
    this.inquiry.investment = {
      investmentId: this.investmentId
    };
    console.log(this.investment);

    this.investmentinquiryService.addInquiry(this.inquiry).subscribe((data) => {
      this.resetInquiry();
      this.showSuccessPopup = true; // Show the success popup
    },
    (error) => {
      console.error('Error submitting Inquiry', error);
    });
  }

  resetInquiry() {
    this.inquiry = {
      user: null,
      investment: null,
      message: '',
      status: 'Pending',
      inquiryDate: new Date(),
      responseDate: new Date(),
      adminResponse: 'No response yet',
      priority: '',
      contactDetails: ''
    };
    this.investment = {
      name: '',
      description: "",
      type: "",
      purchasePrice: 0,
      currentPrice: 0,
      quantity: 0,
      purchaseDate: "",
      status: ""
    };
  }

  closePopup(): void {
    this.showSuccessPopup = false;
    this.router.navigate(['/user/view-inquiry']);
  }
}
