import { Investment } from "./investment.model"
import { User } from "./user.model"

export interface Feedback{
    feedbackId?:number
    feedbackText:string
    date?:Date
    user?:User
    investment?:Investment
    category:string
}