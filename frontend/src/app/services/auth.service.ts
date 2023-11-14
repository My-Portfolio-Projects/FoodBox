import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  setLoggedIn(){
    localStorage.setItem("isLoggedIn","true");
  }
  isLoggedIn(){
    return localStorage.getItem("isLoggedIn");
  }

  setCustomerId(customerId:number){
    localStorage.setItem("customerId",customerId.toString(10));
  }
  getCustomerId():number{
    const x = localStorage.getItem("customerId");
    if(x){
      return parseInt(x);
    }
    else{
      return 0;
    }
  }
  setLoggedOut(){
    localStorage.setItem("isLoggedIn","false");
  }
}
