import {Image} from "./Image";

export class Product {
  constructor(
    public productId:number = 0,
    public name:string = '',
    public description:string = '',
    public price:number = 0,
    public quantityInStock:number = 0,
    public image:Image = new Image()
    // public offer:number,
  ){}
}
