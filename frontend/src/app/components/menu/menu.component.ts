import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from "../../models/Product";
import { FormsModule } from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators'
import { Order } from '../../models/Order';
import { OrderItem } from '../../models/OrderItem';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [HttpClient],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  ProductMap: Map<number, boolean> = new Map<number, boolean>();
  products: Product[] = [];
  searchKeyword: string = '';
  sort: string = '';
  filter: string = '';
  orderItem: OrderItem = new OrderItem();

  onSort() {
    switch (this.sort) {
      case '0':
        this.products.sort((a, b) => a.name.localeCompare(b.name));
        break;

      case '1':
        this.products.sort((a, b) => b.name.localeCompare(a.name));
        break;
      case '2':
        this.products.sort((a, b) => a.price - b.price);
        break;
      case '3':
        this.products.sort((a, b) => b.price - a.price);
        break;
    }
  }

  onFilter() {
    switch (this.filter) {
      case '0':
        this.products.sort((a, b) => a.quantityInStock - b.quantityInStock);
        break;

      case '1':
        this.products.sort((a, b) => b.quantityInStock - a.quantityInStock);
        break;

    }
  }

  ngOnInit() {
    console.log("************Init******************")
    this.getAll().subscribe((products: Product[]) => {
      const orderItemRequests: Observable<boolean>[] = products.map(product =>
        this.getOrderItem(product.productId)
      );

      forkJoin(orderItemRequests).subscribe(orderItems => {
        products.forEach((product, index) => {
          this.ProductMap.set(product.productId, orderItems[index]);
        });

        this.products = products;
      });
    });
  }
  getOrderItem(productId: number): Observable<boolean> {
    return this.http.get<any>('/api/isOrderItem/' + productId).pipe(
      map(isItem => {
        return isItem;
      })
    );
  }

  constructor(private http: HttpClient, private auth: AuthService) { }

  onSearch(): void {
    if (this.searchKeyword.trim() === '') {
      this.getAll().subscribe((products: Product[]) => {
        this.products = products;
      });
    }
    else { this.search(this.searchKeyword).subscribe((products: Product[]) => { this.products = products; }); }
  }
  search(keyword: string): Observable<Product[]> { return this.http.get<Product[]>('/api/search/' + keyword); }
  getAll() { return this.http.get<Product[]>('/api/products'); }

  addToCart(product: Product) {
    let order: Order = new Order();
    const date = new Date();
    const formattedDate = date.toISOString().replace('T', ' ').replace('Z', '');
    const parsedDate: Date = new Date(formattedDate);
    order.customerId = this.auth.getCustomerId();
    order.billingAddress = ""
    order.orderDate = parsedDate;


    this.http.post<OrderItem>("/api/orderItems", product).subscribe((response) => {
      this.orderItem = response;
      this.http.post<Order>("/api/orders", order).subscribe((response) => {
        order = response;
        this.orderItem.orderId = order.orderId;
        this.orderItem.quantity > 0 ? this.orderItem.quantity : this.orderItem.quantity = 1;
        this.http.put("/api/orderItems/" + this.orderItem.orderItemId, this.orderItem).subscribe((response) => { console.log(response) });
        this.ngOnInit();
      });
    });


  }
  removeFromCart(ProductId: number) {
    this.http.delete("/api/orderItems/" + ProductId).subscribe((response) => {
      console.log(response)
      this.ngOnInit();
});
  }

}


