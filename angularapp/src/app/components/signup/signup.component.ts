import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form: FormGroup;
  elementRef: any;
 
  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly fb: FormBuilder
  ) { }
 
  ngOnInit(): void {
    this.initializeForm();
  }
 
  private initializeForm(): void {
    this.form = this.fb.group(
      {
        username: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        mobileNumber: [
          '',
          [Validators.required, Validators.pattern(/^\d{10}$/)]
        ],
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', Validators.required],
        userRole: ['', Validators.required]
      },
      {
        validators: [this.passwordMismatchValidator]
      }
    );
  }
 
  register(): void {
    if (this.form.invalid) {
      return;
    }
 
    this.authService.register(this.form.value).subscribe(
      () => {
        this.form.reset();
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Registration error:', error);
        alert('Registration failed. Please try again later.');
      }
    );
  }
 
  private passwordMismatchValidator(formGroup: FormGroup): void {
    const password = formGroup.get('password');
    const confirmPassword = formGroup.get('confirmPassword');
 
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
    } else if (confirmPassword) {
      confirmPassword.setErrors(null);
    }
  }
 
  showSuccessModal(): void {
    const modal = this.elementRef.nativeElement.querySelector('#successModal');
    if (modal) {
      (window as any).$(`#successModal`).modal('show'); // Use jQuery Bootstrap modal trigger
    }
  }
 
  navigateToLogin(): void {
    (window as any).$(`#successModal`).modal('show'); // Hide modal before navigating
  }
}