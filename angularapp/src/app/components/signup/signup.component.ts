import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form: FormGroup;
  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      email: [''],
      password: [''],
      username: [''],
      mobileNumber: [''],
      userRole: ['Admin']
    })
  }
  register(){
    if(this.form.valid){
      this.authService.register(this.form.value).subscribe(()=>{
        this.form.reset();
        this.router.navigate(["/login"]);
      });
    }
  }
}
