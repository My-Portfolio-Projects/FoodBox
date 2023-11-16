import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  setLoggedIn(){
    localStorage.setItem("adminIsLoggedIn","true");
  }
  isLoggedIn(){
    return localStorage.getItem("adminIsLoggedIn");
  }
  setLoggedOut(){
    localStorage.setItem("adminIsLoggedIn","false");
  }
}
