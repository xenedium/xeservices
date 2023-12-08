import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: any
  constructor(private http: HttpClient) {

  }
  ngOnInit(): void {
    this.http.get("http://192.168.1.2:9999/inventory-service/products?projection=fullProduct").subscribe(
      {
        next: (data) => {
          this.products = data
        },
        error: (err) => {

        }
      }
    )
  }

}
