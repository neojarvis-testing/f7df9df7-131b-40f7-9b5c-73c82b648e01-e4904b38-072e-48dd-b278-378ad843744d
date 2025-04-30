import { Component, OnInit } from '@angular/core';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-user-view-inquiry',
  templateUrl: './user-view-inquiry.component.html',
  styleUrls: ['./user-view-inquiry.component.css']
})
export class UserViewInquiryComponent implements OnInit {


  inquiries: InvestmentInquiry[] = [];

    constructor(private inquiryService: InvestmentInquiryService) {}
  
    ngOnInit(): void {
     this.inquiryService.getAllInquries().subscribe((data) => {
     this.inquiries = data;
     });
   }
  
}
