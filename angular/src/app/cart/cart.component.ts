// import { Component, OnInit } from '@angular/core';
// import { ApiService } from '../service/api.service';
// import { CartService } from '../service/cart.service';
// import { Router } from '@angular/router';
// import { CommonModule } from '@angular/common';

// @Component({
//   selector: 'app-cart',
//   standalone: true,
//   imports: [CommonModule],
//   templateUrl: './cart.component.html',
//   styleUrl: './cart.component.css'
// })
// export class CartComponent implements OnInit {

//   constructor(private apiService:ApiService, private cartService:CartService, private router:Router) {}

//   cart:any[] = [];
//   message: any = null;
//   totalPrice:number = 0;

//   ngOnInit(): void {
//       this.loadCart();
//   }

//   loadCart():void{
//     this.cart = this.cartService.getCart();
//     this.calculateTotalPrice()
//   }

//   calculateTotalPrice():void{
//     this.totalPrice = this.cart.reduce((total, item) => total + item.price * item.quantity, 0)
//   }

//   incrementItem(itemId: number):void{
//     this.cartService.incrementItem(itemId)
//     this.loadCart();
//   }

//   decrementItem(itemId: number):void{
//     this.cartService.decrementItem(itemId)
//     this.loadCart();
//   }

//   removeItem(itemId: number):void{
//     this.cartService.removeItem(itemId)
//     this.loadCart();
//   }
//   clearCart():void{
//     this.cartService.clearCart()
//     this.loadCart();
//   }


//   handleCheckOut():void{
//     if (!this.apiService.isAuthenticated()) {
//       this.message = "you need to login before you can place an order"
//       setTimeout(()=>{
//         this.message = null
//         this.router.navigate(['/login'])
//       }, 3000)
//       return;
//     }

//     const orderItems = this.cart.map(item =>({
//       productId: item.id,
//       quantity: item.quantity
//     }));

//     const orderRequest = {
//       totalPrice: this.totalPrice,
//       items: orderItems
//     }

//     this.apiService.createOrder(orderRequest).subscribe({
//       next:(response)=>{
//         this.message = (response.message)
//         if (response.status === 200) {
//           this.cartService.clearCart();
//           this.loadCart();
//         }
//       },
//       error:(error)=>{
//         console.log(error)
//         this.message = error?.error?.message || "unable to place order"
//       }
//     })
//   }
// }

// //
import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CartService } from '../service/cart.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
// import * as Razorpay from 'razorpay';

declare var Razorpay: any;  // Replace with your Razorpay Key

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {
  cart: any[] = [];
  message: string | null = null;
  totalPrice: number = 0;

  constructor(private apiService: ApiService, private cartService: CartService, private router: Router, private cdr: ChangeDetectorRef ) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cart = this.cartService.getCart();
    this.calculateTotalPrice();
  }

  calculateTotalPrice(): void {
    this.totalPrice = this.cart.reduce((total, item) => total + item.price * item.quantity, 0) || 0;
  }

  incrementItem(itemId: number): void {
    this.cartService.incrementItem(itemId);
    this.loadCart();
  }

  decrementItem(itemId: number): void {
    this.cartService.decrementItem(itemId);
    this.loadCart();
  }

  removeItem(itemId: number): void {
    this.cartService.removeItem(itemId);
    this.loadCart();
  }

  clearCart(): void {
    this.cartService.clearCart();
    this.loadCart();
  }

  handleCheckOut(): void {
    if (!this.apiService.isAuthenticated()) {
      this.message = "You need to login before you can place an order";
      setTimeout(() => {
        this.message = null;
        this.router.navigate(['/login']);
      }, 3000);
      return;
    }

    const orderItems = this.cart.map(item =>({
      productId: item.id,
      quantity: item.quantity
    }));

    const orderRequest = {
      totalPrice: this.totalPrice,
      items: orderItems
    }

    this.apiService.createOrder(orderRequest).subscribe({
      next:(response)=>{
        // this.message = (response.message)
        if (response.status === 200) {
          // this.cartService.clearCart();
          this.loadCart();
        }
      },
      error:(error)=>{
        console.log(error)
        this.message = error?.error?.message || "unable to place order"
      }
    })
    
    this.apiService.createTransaction(this.totalPrice).subscribe({
      next: (response: any) => {
        if (response) {
          this.openTransactionModal(response);
        } else {
          alert("Payment failed");
        }
      },
      error: (error) => {
        console.error("Transaction Error:", error);
        this.message = error?.error?.message || "Unable to process transaction";
      }
    });
  }
  
  openTransactionModal(response: any): void {
    var options = {
      order_id: response.id, 
      key: response.key,  
      amount: response.amount, 
      currency: response.currency,
      name: 'E-commerce Store',
      description: 'Payment for items in cart',
      image: 'https://cdn.pixabay.com/photo/2023/01/22/13/46/swans-7736415_640.jpg',  
      //  image: 'C:\Users\Admin\Desktop\Images\payment.jpg',
      handler: (res: any) => {
        console.log('Payment Success:', res);
        alert('Payment Successful! Order ID: ' + res.razorpay_payment_id);

        setTimeout(() => {
          this.loadCart();
          this.cartService.clearCart();
          this.loadCart(); 
          this.cdr.detectChanges();
        }, 5);
      
      },
      prefill: {
        name: 'Dharma',
        email: 'dharmaamdg@gmail.com',
        contact: '6303111988'
      },
      theme: { color: '#f68b1e' },
      method: {  
        card: true,
        netbanking: true,
        wallet: true,
        upi: true,  
        emi: true, 
        paylater: true
      }
    };
  
    const rzp = new Razorpay(options);
    rzp.open();
  } 
} 