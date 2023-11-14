import {Component, ViewChild} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, NgForm} from "@angular/forms";
import {Customer} from "../../models/Customer";
import {HttpClient} from "@angular/common/http";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @ViewChild("l") loginForm!: NgForm;

  submitted = false;
  user!: Customer;
  constructor(private http: HttpClient, private router: Router,private authService:AuthService) {
    this.user = new Customer();
  }

  ngOnInit(): void {this.user = new Customer();}
  onSubmit() {
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    console.log(this.loginForm)
    this.http.post('/api/customer/login?email=' + email + '&password=' + password,null)
      .subscribe((loginResponse:any) => {
        this.router.navigate(['/user/' + loginResponse]);
        this.authService.setLoggedIn();
        this.authService.setCustomerId(loginResponse);
        this.submitted = true;
        this.loginForm.reset();
      });
  }
}
