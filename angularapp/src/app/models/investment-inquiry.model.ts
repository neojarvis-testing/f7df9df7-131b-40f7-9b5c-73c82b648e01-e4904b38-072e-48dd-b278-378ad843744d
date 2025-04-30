import { Investment } from "./investment.model";
import { User } from "./user.model";

export interface InvestmentInquiry{
    inquiryId?:number;
    user?: User;
    investment?: Investment;
    message?: string;
    status?:string;
    inquiryDate?:Date;
    responseDate?:Date;
    adminResponse?:string;
    priority?:string;
    contactDetails?:string;
}