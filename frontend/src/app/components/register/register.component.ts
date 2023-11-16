import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, NgForm} from "@angular/forms";
import {Customer} from "../../models/Customer";
import {HttpClient} from "@angular/common/http";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  @ViewChild('r') registerForm!: NgForm;
  @Output() registerEvent = new EventEmitter<Customer>;

  public customer!: Customer;
  public matched: boolean;
  public reenter: any;

  constructor(private http: HttpClient) {
    this.customer = new Customer();
    this.matched = false;
  }
  ngDoCheck(): void {
    this.matched = this.reenter === this.customer.password;
  }


  onSubmit() {
    this.customer.firstName = this.registerForm.value.firstname;
    this.customer.lastName = this.registerForm.value.lastname;
    this.customer.email = this.registerForm.value.email;
    this.customer.password = this.registerForm.value.password;
    this.customer.address = this.registerForm.value.address;
    this.customer.phoneNumber = this.registerForm.value.phoneNumber
    console.log(this.customer);
    this.http.post('api/customers', JSON.stringify(this.customer), { headers: { 'Content-Type': 'application/json' } })
      .subscribe(registerResponse => {
        console.log(registerResponse)
        this.registerForm.reset();
      });

  }
}
