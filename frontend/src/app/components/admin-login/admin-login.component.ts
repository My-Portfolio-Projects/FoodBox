import {Component, ViewChild} from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClient} from "@angular/common/http";
import {FormsModule, NgForm} from "@angular/forms";
import {Customer} from "../../models/Customer";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AdminAuthService} from "../../services/admin-auth.service";

@Component({
  selector: 'app-admin-login',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent {
  @ViewChild("l") loginForm!: NgForm;

  submitted = false;
  user!: Customer;
  constructor(private http: HttpClient, private router: Router,private authService:AdminAuthService) {
    this.user = new Customer();
  }

  ngOnInit(): void {this.user = new Customer();}
  onSubmit() {
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    console.log(this.loginForm)
    this.http.post('/api/admins/login?email=' + email + '&password=' + password,null)
      .subscribe((loginResponse:any) => {
        this.router.navigate(['/admin/' + loginResponse]);
        this.authService.setLoggedIn();
        this.submitted = true;
        this.loginForm.reset();
      });
  }
}
