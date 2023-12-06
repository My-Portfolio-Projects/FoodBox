export class Customer {

  constructor(
    public customerId:number = 0,
    public firstName = '',
    public lastName = '',
    public email= '',
    public password = '',
    public address = '',
    public phoneNumber:number = 0
  ){}
}
