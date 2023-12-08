import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit{
  customers:any
  constructor(private http:HttpClient,private router:Router){  
  }
  getOrders(c:any) {
    this.router.navigateByUrl('/orders/'+c.id)
  }
  ngOnInit(): void {
    this.http.get("http://192.168.1.2:9999/customer-service/customers?projection=fullCustomer").subscribe(
      {
        next:(data)=>{
          this.customers=data
        },
        error:(err)=>{

        }
      }
    )
  }
 

}
