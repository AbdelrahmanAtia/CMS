import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/admin/_services/order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/admin/_models/Order';
import { Response } from 'src/app/admin/_models/Response';
import { Config } from 'src/app/admin/_models/Config ';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  orderStatusList: string[] = [];
  orderStatus:string;
  pageNumber: number;
  totalPages: number;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.initializeOrderStatusList();
    this.listenToRouteParamChanges();
  }

  private initializeOrderStatusList() {
    this.orderService.getOrderStatusList().subscribe(
      (response:string[]) => { 
        this.orderStatusList = response;
      },
      (error) => { console.log(error) }
    );
  }

  // called when any route param changes
  private listenToRouteParamChanges():void {
    this.route.params.subscribe(
      params => {
        this.orderStatus = params['orderStatus'];
        this.pageNumber = +params['pageNumber'];
        this.initializeOrdersList();
      });
  }

  private initializeOrdersList() {
    this.orderService.getOrders(this.orderStatus, this.pageNumber).subscribe(
      (response) => {
        this.totalPages = +response.headers.get('totalPages');
        this.orders = response.body;
      }, (error) => {console.log(error)}
    )
  }

  getOrders(orderStatus: string):void {
    this.router.navigate(['admin/main', 'orders', orderStatus, '1']);
  }

  addNewOrder(): void { 
    this.router.navigate(Config.addNewOrderRoute);
  }

  editOrder(orderId: number): void {
    this.router.navigate(['admin/main', 'orders', orderId, 'edit']);
  }

  deleteOrder(orderId: number): void {

    if (!confirm("Are you sure you want to delete the selected record?")) {
      return;
    }

    this.orderService.deleteOrder(orderId).subscribe(
      (response: Response) => {
        if (response.status) {
          if(this.pageNumber > 1 && this.totalPages == this.pageNumber && this.orders.length == 1){
            //go to the previous page 
            this.router.navigate(['admin/main', 'orders', this.orderStatus, this.pageNumber - 1]);
          } else {
            //stay in the same page
            this.router.navigate(['admin/main', 'orders', this.orderStatus, this.pageNumber, new Date().getTime()]);
          }
        }  
        else {
          throw new Error(response.message);
        }
      },
      (error) => { 
        console.log(error); 
      }
    );
  }
  
  onPageChange(i: number): void {
    let pageNumber:number = this.pageNumber + i;
    this.router.navigate(['admin/main', 'orders', this.orderStatus, pageNumber]);
  }

}
