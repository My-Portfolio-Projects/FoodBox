  import { Component } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { Product } from "../../models/Product";
  import { FormsModule } from "@angular/forms";
  import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
  import { Observable, switchMap } from 'rxjs';
  import { MenuComponent } from "../menu/menu.component";

  @Component({
    selector: 'app-admin',
    standalone: true,
    providers:[HttpClient],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css',
    imports: [CommonModule, FormsModule, HttpClientModule, MenuComponent]
  })


  export class AdminComponent {
    products: Product[] = [];

    image!: File;
    newProduct: Product = new Product();

    searchKeyword: string = '';
    sort: string = '';
    filter: string = '';
    headers = new HttpHeaders().set('content-type', 'multipart/form-data');
    constructor(private http: HttpClient) { }
    ngOnInit() { this.getAll().subscribe((products: Product[]) => { this.products = products; }); }
    onSubmit() { this.addItem(this.newProduct, this.image); }


    addItem(product: Product, imageData: any) {
      const formData = new FormData();
      formData.append('image', imageData);

      this.addImage(formData).pipe(
        switchMap((imageId: number) => {
          this.newProduct.image.id = imageId;
          console.log("This is imageid:" + imageId);
          const addProduct = {
            "description": `${product.description}`,
            "imageId": `${imageId}`, // Use the updated imageId from the response
            "name": `${product.name}`,
            "price": `${product.price}`,
            "quantityInStock": `${product.quantityInStock}`,
          };
          console.log(addProduct);
          return this.http.post("/api/products", addProduct);
        })
      ).subscribe((response) => {
        console.log(response);
      });
    }


    addImage(formData: FormData): Observable<number> { return this.http.post<number>("/api/image", formData); }

    onImageFileSelected(event: any) { this.image = event.target.files[0]; }


    onSearch(): void {
      if (this.searchKeyword.trim() === '') { this.getAll().subscribe((products: Product[]) => { this.products = products; }); }
      else { this.search(this.searchKeyword).subscribe((products: Product[]) => { this.products = products; }); }
    }


    search(keyword: string): Observable<Product[]> { return this.http.get<Product[]>('/api/search/' + keyword); }
    getAll() { return this.http.get<Product[]>('/api/products'); }

    deleteItem(item: Product) { this.http.delete("/api/products/" + { item }).subscribe((response) => { console.log(response); }); }

    updateItem(item: Product) { this.updateItem(item); this.http.put("/api/products/", { item }).subscribe((response) => { console.log(response); }); }

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
  }

