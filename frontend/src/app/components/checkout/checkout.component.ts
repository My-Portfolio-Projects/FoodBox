import {Component,  OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule} from "@angular/forms";
import {OrderItem} from "../../models/OrderItem";
import { loadStripe } from '@stripe/stripe-js';
import { Product } from '../../models/Product';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit {

 stripe:any;
  sessionId!: string;

    async ngOnInit() {
     this.stripe = await loadStripe("pktest51O1ktxSDatT9yImLN79uIYq7nwCQNUwGFqsmz69yD74McgEh6UUeCf2DMPezJg4ar63JERPXDu4zolnbFfVxhJcX000LhDi0Zo")
      await this.initialize();
 }
 async initialize(){
   const response = await fetch("/create-checkout-session",{
     method:"POST",
   });
   const  {clientSecret,sessionId} = await response.json();
   const checkout = await this.stripe.initEmbeddedCheckout(
     clientSecret,
   );
     checkout.mount('#checkout');

 }
    async checkSessionStatus() {
        const sessionResponse = await fetch(`/sessionstatus?sessionid=${this.sessionId}`);
        const session = await sessionResponse.json();

        if (session.status === 'open') {
            // Remount embedded Checkout or handle open status as needed
        } else if (session.status === 'complete') {
            alert("Payment Successful!")
        }
    }
items: OrderItem[][] = [];
  amount: number = 0;
  constructor(private http:HttpClient) {}


  getAmount(array:OrderItem[][]):number{
    for(const order of array){
      for (const item of order){
        this.amount += item.price;
      }
    }
    return this.amount;
  }
  getItemName(id: number): string {
    let productName !: string
    this.http.get<Product>("/api/products" + id).subscribe((product) => {
      productName = product.name;
    });
    return productName;
  }

  getItemDesc(productId: number) {

    let Desc!: string;
    this.http.get<Product>("/api/products" + productId).subscribe((product) => {
      Desc = product.description;
    });
    return Desc;
  }


}


