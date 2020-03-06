import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/_services/order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/_models/Order';
import { Response } from 'src/app/_models/Response';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  orderStatusList: string[] = [];
  orderStatus:string;

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
        this.initializeOrdersList();
      });
  }

  private initializeOrdersList() {
    this.orderService.getOrders(this.orderStatus).subscribe(
      (response) => {
        this.orders = response.body;
      }, (error) => {console.log(error)}
    )
  }

  getOrders(orderStatus: string):void {
    this.router.navigate(['main', 'orders', orderStatus]);
  }

  addNewOrder(): void {
    this.router.navigate(['main', 'orders', 'new']);
  }

  editOrder(orderId: number): void {
    this.router.navigate(['main', 'orders', orderId, 'edit']);
  }

  deleteOrder(orderId: number): void {

    if (!confirm("Are you sure you want to delete the selected record?")) {
      return;
    }

    this.orderService.deleteOrder(orderId).subscribe(
      (response: Response) => {
        if (response.status = "200") {
          this.ngOnInit();
        }
        else if (response.status = "404") {
          throw new Error(response.message);
        }
      },
      (error) => { console.log(error); }
    );
  }

}
