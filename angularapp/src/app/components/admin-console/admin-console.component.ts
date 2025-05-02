import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-console',
  templateUrl: './admin-console.component.html',
  styleUrls: ['./admin-console.component.css']
})
export class AdminConsoleComponent implements OnInit {
  noOfFeeddback: number = 0;
  noOfInquiries: number = 0;
  noOfInvestment: number = 0;
  constructor(private readonly feedbackService: FeedbackService, private readonly investmentService: InvestmentService, private readonly investmentInquiryService: InvestmentInquiryService) { }

  ngOnInit(): void {
    this.feedbackService.getFeedbacks().subscribe((data)=>{
      this.noOfFeeddback = data.length;
    })
    this.investmentInquiryService.getAllInquries().subscribe((data)=>{
      this.noOfInquiries = data.length;
    })
    this.investmentService.getAllInvestments().subscribe((data)=>{
      this.noOfInvestment = data.length;
    })
  }

}
