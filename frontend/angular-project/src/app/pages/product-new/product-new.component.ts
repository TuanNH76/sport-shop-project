import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../models/productInfo";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-product-new',
    templateUrl: './product-new.component.html',
    styleUrls: ['./product-new.component.css']
})
export class ProductNewComponent implements OnInit, AfterContentChecked {

    product : ProductInfo;

    constructor(private productService: ProductService,
                private route: ActivatedRoute,
                private router: Router) {
                    this.product= new ProductInfo();
    }


    ngOnInit() {
        
            
        }

    onSubmit() {
        this.productService.create(this.product).subscribe(prod => {
            if (!prod) throw new Error;
            this.router.navigate(['/']);
        },
              (error) => {
                // Xử lý lỗi khi thêm sản phẩm
                console.error("Error", error);
              }
            );
          }

    ngAfterContentChecked(): void {
        console.log(this.product);
    }
}