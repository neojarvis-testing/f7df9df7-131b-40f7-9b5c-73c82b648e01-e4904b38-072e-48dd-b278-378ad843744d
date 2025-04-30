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

  inquiry: InvestmentInquiry = {
    user: null,
    investment: null,
    message: '',
    status: 'Pending',
    inquiryDate: new Date()+'',
    responseDate: '',
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
  };  // Initialize properly

  constructor(private investmentinquiryService: InvestmentInquiryService, private investmentService: InvestmentService, 
    private router: Router, private activatedRoute: ActivatedRoute) { }

  addInquiry() {
    this.inquiry.investment = this.investment;  // Assign investment before submitting
    this.investmentinquiryService.addInquiry(this.inquiry).subscribe((data) => {
      this.resetInquiry();
      alert("Inquiry submitted successfully!")
      this.router.navigate(['/user/view-inquiry']);
    });
  }

  ngOnInit(): void {
    let investmentId = +this.activatedRoute.snapshot.params['investmentId'];
    this.investmentService.getInvestmentById(investmentId).subscribe((data) => {
      this.investment = data;
    });
  }

  resetInquiry() {
    this.inquiry = {
      user: null,
      investment: null,
      message: '',
      status: 'Pending',
      inquiryDate: new Date()+'',
      responseDate: '',
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
    };  // Reset investment properly
  }
}