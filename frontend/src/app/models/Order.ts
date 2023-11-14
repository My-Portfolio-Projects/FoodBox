
export class Order{

  constructor(
    public orderId:number=0,
    public customerId:number=0,
    public orderDate:Date = new Date(),
    public orderTotal:number=0,
    public shippingAddress:string = '',
    public billingAddress:string = ''
  ) {}
}
