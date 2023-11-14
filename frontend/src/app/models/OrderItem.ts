export class OrderItem{
  
  constructor(
    public orderItemId:number = 0,
    public orderId:number = 0,
    public productId:number = 0,
    public quantity:number = 0,
    public price:number = 0

  ) {
  }
}
