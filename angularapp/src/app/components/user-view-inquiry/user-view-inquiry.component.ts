import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-user-view-inquiry',
  templateUrl: './user-view-inquiry.component.html',
  styleUrls: ['./user-view-inquiry.component.css']
})
export class UserViewInquiryComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
  }

}
