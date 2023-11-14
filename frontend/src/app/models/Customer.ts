export class Customer {
 
  constructor(
    public customerId:number = 0,
    public firstName = '',
    public lastName = '',
    public email = '',
    public password:string = '',
    public address = '',
    public phoneNumber:number = 0
  ){}
}
