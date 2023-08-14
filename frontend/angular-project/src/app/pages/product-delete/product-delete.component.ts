import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {ProductInfo} from "../../models/productInfo";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-product-delete',
    templateUrl: './product-delete.component.html',
    styleUrls: ['./product-delete.component.css']
})
export class ProductDeleteComponent implements OnInit, AfterContentChecked {

    product : ProductInfo
    constructor(private productService: ProductService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    productId: string;

    ngOnInit() {
        this.productId = this.route.snapshot.paramMap.get('id');
        if (this.productId) {
            this.productService.getDetail(this.productId).subscribe(prod => this.product = prod);
        }

    }

    delete() {
        this.productService.delete(this.product).subscribe(() => {
                this.router.navigate(['/seller']);
            },
            (error) => {
                console.error("Error at Delete Product", error);
            });

    }

    onSubmit() {
        if (this.productId) {
            this.delete();
        } 
        // else {
        //     this.add();
        // }
    }

    // add() {
    //     this.productService.create(this.product).subscribe(prod => {
    //             if (!prod) throw new Error;
    //             this.router.navigate(['/']);
    //         },
    //         e => {
    //         });
    // }

    ngAfterContentChecked(): void {
        console.log(this.product);
    }
}