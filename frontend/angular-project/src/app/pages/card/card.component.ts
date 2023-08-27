import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit, OnDestroy {


  title: string;
  page: any;
  bestSellerPage : any;
  private paramSub: Subscription;
  private querySub: Subscription;
  showBestSellers: boolean = false;

  
  constructor(private productService: ProductService,
              private route: ActivatedRoute) {

  }


  ngOnInit() {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });
    this.paramSub = this.route.params.subscribe(() => {
      this.update();
    });
    this.getBestSelletProds();
    this.update();
  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
    this.paramSub.unsubscribe();
  }

  update() {
    if (this.route.snapshot.queryParamMap.get('page')) {
      const currentPage = +this.route.snapshot.queryParamMap.get('page');
      const size = +this.route.snapshot.queryParamMap.get('size');
      this.getProds(currentPage, size);
    } else {
      this.getProds();
    }
  }
  getProds(page: number = 1, size: number = 12) {
    if (this.route.snapshot.url.length == 1) {
      this.productService.getAllInPage(+page, +size)
        .subscribe(page => {
          this.page = page;
          this.title = ' Welcome to Sport Shop '
        });
    } else { //  /category/:id
      const type = this.route.snapshot.url[1].path;
      this.productService.getCategoryInPage(+type, page, size)
        .subscribe(categoryPage => {
          this.title =categoryPage.category ;
          this.page = categoryPage.page;
          this.hideBestSellers();
        });
    }

  }

  getBestSelletProds(){
    this.productService.getByTopSale().subscribe(page =>{
      this.bestSellerPage=page;
      this.title = 'Products'
      this.showBestSellers = true;
    }
      );
  }

  searchProductsByName(page: number = 1, size: number = 12,name: string) {
    this.productService.getAllByName(+page, +size, name)
      .subscribe(page => {
        this.page = page;
        this.title = `Search results for "${name}"`;
        this.hideBestSellers();
      });
  }

  hideBestSellers() {
    this.showBestSellers = false;
  }
}
