import { Component, OnInit } from '@angular/core';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-user-view-inquiry',
  templateUrl: './user-view-inquiry.component.html',
  styleUrls: ['./user-view-inquiry.component.css']
})
export class UserViewInquiryComponent implements OnInit {
  decryptedLogin = JSON.parse(atob(localStorage.getItem('encryptedLogin'))); // Decode & parse JSON
  userId = this.decryptedLogin.userId; // Access property
  //userId: number = +localStorage.getItem('userId');
  inquiries: InvestmentInquiry;

  // Injecting the InvestmentInquiryService to fetch inquiries from the backend
  constructor(private readonly inquiryService: InvestmentInquiryService) {}
  
    // Lifecycle hook executed when the component initializes
    ngOnInit(): void {
     this.inquiryService.getInquriesByUserId(this.userId).subscribe((data) => {
     this.inquiries = data;
     });
   }
  
}
