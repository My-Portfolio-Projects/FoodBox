import { Routes, provideRouter } from '@angular/router';

import { MenuComponent } from './components/menu/menu.component';
import { AdminComponent } from './components/admin/admin.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { AdminRegisterComponent } from './components/admin-register/admin-register.component';
import { HomeComponent } from './components/home/home.component';
import { CartComponent } from './components/cart/cart.component';
import { RegisterComponent } from './components/register/register.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { adminAuthGuard } from '../AuthGuards/admin-auth.guard';
import { customerAuthGuard } from '../AuthGuards/customer-auth.guard';

export const routes: Routes = [
    { path:'search', component:MenuComponent},

    { path:'admin', component:AdminComponent,canActivate:[adminAuthGuard] },

    { path: 'admin/login', component: AdminLoginComponent },

  { path: 'admin/register', component:AdminRegisterComponent},
  { path: 'user/:id', component:HomeComponent,canActivate:[customerAuthGuard]},
  { path: 'admin/:id', component: AdminComponent, canActivate: [adminAuthGuard] },

  {path:'cart', component:CartComponent,canActivate:[customerAuthGuard]},

  { path: 'checkout', component: CheckoutComponent,canActivate:[customerAuthGuard]},

  {path:'',redirectTo:'home',pathMatch:"full"},


  { path: 'home',component:HomeComponent},

  {path:'login',component:LoginComponent},

  {path:'register', component:RegisterComponent},


  { path: '404', component: PageNotFoundComponent },
  { path: '**', redirectTo: '404' },

];
