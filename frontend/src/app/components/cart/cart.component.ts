  import { Component, OnInit } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { OrderItem } from "../../models/OrderItem";
  import { HttpClient } from '@angular/common/http';
  import { AuthService } from '../../services/auth.service';
  import { Product } from '../../models/Product';
  import {  forkJoin } from 'rxjs';


  @Component({
    selector: 'app-cart',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './cart.component.html',
    styleUrl: './cart.component.css'
  })
  export class CartComponent implements OnInit {

    orders: OrderItem[][] = [];
    ProductNames: Map<number, string> = new Map<number, string>();
    constructor(private http: HttpClient, private authService: AuthService) { }
    ngOnInit() {this.getItems();}

    getItems(): any {
      this.http.get<number[]>("/api/orders/" + this.authService.getCustomerId()).subscribe((customerOrderIds) => {
        const orders = customerOrderIds.map(orderId =>
          this.http.get<OrderItem[]>("/api/orderItems/" + orderId)
        );
        forkJoin(orders).subscribe((ordersList: OrderItem[][]) => {
          this.orders = ordersList;
          this.getItemNames();
        })
      });
    }

    getItemNames() {
      for (let order of this.orders) {
        for (let item of order) {
          this.http.get("/api/productname/" + item.productId, {responseType:'text'}).subscribe((productName) => {
            this.ProductNames.set(item.productId, productName);
            console.log(productName);
          });
        }
      }
    }

    getItemDesc(productId: number): string {
      return this.http.get<Product>("/api/products/" + productId).subscribe((product) =>
      { return product.description; }).toString();
    }
  onDelete(item: OrderItem) {this.http.delete("/api/orderItems/" + item.productId);}
  clear() {this.http.delete("/api/orderItems");}
  public add(item: OrderItem) {this.http.post("/api/orderItems", item);}

  }
