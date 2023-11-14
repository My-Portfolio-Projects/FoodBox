import {Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Product} from "../../models/Product";
import {FormsModule} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { Observable,forkJoin } from 'rxjs';
import {map} from 'rxjs/operators'
@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers:[HttpClient],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  ProductMap: Map<number, boolean> = new Map<number, boolean>();
  products: Product[]=[];
  searchKeyword: string = '';
  sort: string = '';
  filter: string = '';
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
        console.log(isItem)
        return isItem; 
      })
    );
  }

  constructor(private http: HttpClient) { }

  onSearch():void{
    if (this.searchKeyword.trim() === '') {
      this.getAll().subscribe((products: Product[]) => {
        this.products = products;
      });
    }
    else {this.search(this.searchKeyword).subscribe((products: Product[]) => {this.products = products;});}
  }
  search(keyword: string): Observable<Product[]> {return this.http.get<Product[]>('/api/search/' + keyword);}
  getAll() { return this.http.get<Product[]>('/api/products'); }
  
  addToCart(t33: Product) {
    throw new Error('Method not implemented.');
  }
  removeItem(arg0: number) {
    throw new Error('Method not implemented.');
  }
 
}


