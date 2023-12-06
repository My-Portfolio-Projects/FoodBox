  import { Component } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { Product } from "../../models/Product";
  import { FormsModule } from "@angular/forms";
  import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
  import { Observable, switchMap } from 'rxjs';
  import { MenuComponent } from "../menu/menu.component";
import { Customer } from '../../models/Customer';

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
    Customers: Customer[] = [];
    newCustomer: Customer = new Customer();
    headers = new HttpHeaders().set('content-type', 'multipart/form-data');
    constructor(private http: HttpClient) { }
    ngOnInit() {
      this.http.get<Customer[]>("/api/customers").subscribe((customers) => {
        this.Customers = customers;
      });
     }
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
            "imageId": {
              "image": {
                "imageData": imageData,
                "imageId":imageId

              }
            }, // Use the updated imageId from the response
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
    deleteItem(item: Product) { this.http.delete("/api/products/" + {item}).subscribe((response) => { console.log(response); }); }
    updateItem(item: Product) { this.updateItem(item); this.http.put("/api/products/", { item }).subscribe((response) => { console.log(response); window.location.reload(); }); }

    onUpdate(customer: Customer) {
      this.http.put("/api/customers/" + customer.customerId,customer).subscribe((response)=>{console.log(response)})
    }
    onDelete(customer: Customer) {
      this.http.delete("/api/customers/" + customer.customerId).subscribe((response) => { console.log(response); window.location.reload(); })
    }
    onAdd(customer: Customer) {
      this.http.post("/api/customers", customer).subscribe((response) => { console.log(response); window.location.reload(); })
    }
  }

