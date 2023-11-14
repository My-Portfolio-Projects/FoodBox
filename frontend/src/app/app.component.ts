import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {NavComponent} from "./components/nav/nav.component";
import {HomeComponent} from "./components/home/home.component";
import {CheckoutComponent} from "./components/checkout/checkout.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MenuComponent} from "./components/menu/menu.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
      CommonModule,
      HttpClientModule,
      RouterOutlet,
      RouterLink,
      RouterLinkActive,
      NavComponent,
      HomeComponent,
      CheckoutComponent,
      MenuComponent
  ],
    providers:[HttpClient],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Foodbox';
}
