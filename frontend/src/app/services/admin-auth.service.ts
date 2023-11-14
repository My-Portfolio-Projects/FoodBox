import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  setLoggedIn(){
    localStorage.setItem("isLoggedIn","true");
  }
  isLoggedIn(){
    return localStorage.getItem("isLoggedIn");
  }
  setLoggedOut(){
    localStorage.setItem("isLoggedIn","false");
  }
}
