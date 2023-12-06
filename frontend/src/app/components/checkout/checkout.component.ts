import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from "@angular/forms";
import { OrderItem } from "../../models/OrderItem";
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit {



  items: OrderItem[][] = [];
  ProductNames: Map<number, string> = new Map<number, string>();

  amount: number = 0;
  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }
  ngOnInit(): void {
    this.getItems();
  }

  getItems(): any {
    this.http.get<number[]>("/api/orders/" + this.authService.getCustomerId()).subscribe((customerOrderIds) => {
      const orders = customerOrderIds.map(orderId =>
        this.http.get<OrderItem[]>("/api/orderItems/" + orderId)
      );
      forkJoin(orders).subscribe((ordersList: OrderItem[][]) => {
        this.items = ordersList;
        this.getItemNames();
      })
    });
  }

  getItemNames() {
    for (let order of this.items) {
      for (let item of order) {
        this.http.get("/api/productname/" + item.productId, { responseType: 'text' }).subscribe((productName) => {
          this.ProductNames.set(item.productId, productName);
        });
      }
    }
  }
  getAmount(): number {
    this.amount = 0;
    for (const order of this.items) {

      for (const item of order) {

        this.amount += item.price;
      }
    }
    return this.amount;
  }


  onPayment() {
    console.log("amount:" + this.amount, "customerId:" + this.authService.getCustomerId());
    this.http.post("/api/payment/" + this.authService.getCustomerId(), this.amount).subscribe((response) => {
      if (response) {
        console.log(response);
        this.router.navigate(["/payment-success"]);

      }
      else {
        console.log(response);
        this.router.navigate(["/payment-error"]);

      }
    });
  }


}


