import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { AdminAddInvestmentComponent } from './components/admin-add-investment/admin-add-investment.component';
import { AdminEditInvestmentComponent } from './components/admin-edit-investment/admin-edit-investment.component';
import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';
import { AdminConsoleComponent } from './components/admin-console/admin-console.component';
import { UserViewInvestmentComponent } from './components/user-view-investment/user-view-investment.component';
import { UserAddInquiryComponent } from './components/user-add-inquiry/user-add-inquiry.component';
import { UserViewInquiryComponent } from './components/user-view-inquiry/user-view-inquiry.component';
import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';
import { ErrorComponent } from './components/error/error.component';
import { AdminViewInvestmentComponent } from './components/admin-view-investment/admin-view-investment.component';

const routes: Routes = [
  {path:'signup', component: SignupComponent},
  {path:'login', component: LoginComponent},
  {path:'home', component: HomePageComponent},
  {path:'admin', component: AdminnavComponent},
  {path:'user', component: UsernavComponent},
  {path:'admin/add-investment', component: AdminAddInvestmentComponent},
  {path:'admin/view-investment', component: AdminViewInvestmentComponent},
  {path:'admin/edit-investment/:investmentId', component: AdminEditInvestmentComponent},
  {path:'admin/view-inquiries', component:AdminViewInquiryComponent},
  {path:'admin/view/feedback', component:AdminViewFeedbackComponent},
  {path:'admin/console', component:AdminConsoleComponent},
  {path:'user/view-investment', component:UserViewInvestmentComponent},
  {path:'user/add-inquiry/:inquiryId', component:UserAddInquiryComponent},
  {path:'user/view-inquiry', component:UserViewInquiryComponent},
  {path:'user/add/feedback', component:UserAddFeedbackComponent},
  {path:'user/view-feedback', component:UserViewFeedbackComponent},
  {path:'', redirectTo:'/login', pathMatch:'full'},
  {path:'**', component:ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
