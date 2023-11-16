import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Admin} from "../../models/Admin";
import {FormsModule, NgForm} from "@angular/forms";

@Component({
  selector: 'app-admin-register',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './admin-register.component.html',
  styleUrl: './admin-register.component.css'
})
export class AdminRegisterComponent {

  @ViewChild('r') registerForm!: NgForm;
  @Output() registerEvent = new EventEmitter<Admin>;

  submitted: boolean;
  public admin!: Admin;
  public matched: boolean;
  public reenter: any;

  constructor(private http: HttpClient) {
    this.admin = new Admin();
    this.matched = false;
    this.submitted = false;
  }

  ngDoCheck(): void {
    this.matched = this.reenter === this.admin.password;
  }

  onSubmit() {


    this.admin.email = this.registerForm.value.email;
    this.admin.password = this.registerForm.value.password;
    this.submitted = true;
    console.log(this.admin)
    this.http.post('api/admin/register',this.admin )
      .subscribe((registerResponse: any) => {
        console.log(registerResponse)
        this.registerForm.reset();

      });

  }
}
