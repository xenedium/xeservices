import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderDetails: any
  orderId!: number;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.orderId = this.route.snapshot.params['orderId'];
  }
  ngOnInit(): void {
    
    this.http.get("http://192.168.1.2:9999/order-service/fullOrder/"+this.orderId).subscribe(
      {
        next: (data) => {
          this.orderDetails = data
        },
        error: (err) => {

        }
      }
    )
  }
 
}
