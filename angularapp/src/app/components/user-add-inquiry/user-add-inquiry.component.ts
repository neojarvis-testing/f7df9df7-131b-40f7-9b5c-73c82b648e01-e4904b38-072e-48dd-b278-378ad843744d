import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Investment } from 'src/app/models/investment.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})
export class UserAddInquiryComponent implements OnInit {

  investmentinquiryForm: FormGroup
  investment: Investment;

  constructor(private investmentinquiryService: InvestmentInquiryService, private fb: FormBuilder, private router: Router) { 
    this.investmentinquiryForm=this.fb.group({
      investment:['', Validators.required],
      message:['', Validators.required],
      priority:['', Validators.required]
    })
  }

  addInquiry(){
    this.investmentinquiryService.addInquiry(this.investmentinquiryForm.value).subscribe((data)=>{
        this.investmentinquiryForm.reset();
        //pop up window
        this.router.navigate(['/user/view-inquiry']);
    })
  }

  ngOnInit(): void {
  }

}
