import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OrderService} from "../../services/order.service";
import {Order} from "../../models/Order";
import {OrderStatus} from "../../enum/OrderStatus";
import {UserService} from "../../services/user.service";
import {JwtResponse} from "../../response/JwtResponse";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {Role} from "../../enum/Role";

@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, OnDestroy {

    page: any;
    OrderStatus = OrderStatus;
    currentUser: JwtResponse;
    Role = Role;
    constructor(private httpClient: HttpClient,
                private orderService: OrderService,
                private userService: UserService,
                private route: ActivatedRoute
    ) {
    }

    querySub: Subscription;

    ngOnInit() {
        this.currentUser = this.userService.currentUserValue;
        this.querySub = this.route.queryParams.subscribe(() => {
            this.update();
        });

    }

    update() {
        let nextPage = 1;
        let size = 10;
        if (this.route.snapshot.queryParamMap.get('page')) {
            nextPage = +this.route.snapshot.queryParamMap.get('page');
            size = +this.route.snapshot.queryParamMap.get('size');
        }
        this.orderService.getPage(nextPage, size).subscribe(page => this.page = page, _ => {
            console.log("Get Order Failed")
        });
    }


    cancel(order: Order) {
        this.orderService.cancel(order.orderId).subscribe(res => {
            if (res) {
                order.orderStatus = res.orderStatus;
            }
        });
    }

    finish(order: Order) {
        this.orderService.finish(order.orderId).subscribe(res => {
            if (res) {
                order.orderStatus = res.orderStatus;
            }
        })
    }
    downloadExcel() {
        this.orderService.downloadExcelFile().subscribe(blob => {
          const filename = this.getFilenameFromResponseHeaders(blob);
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = filename;
          a.click();
          window.URL.revokeObjectURL(url);
        });
      }
      getFilenameFromResponseHeaders(blob: Blob): string {
        const contentDisposition = blob.type === 'application/json' ?
          blob['headers'].get('Content-Disposition') : null;
        if (contentDisposition) {
          const filenameMatch = contentDisposition.match(/filename="?(.+)"?/);
          if (filenameMatch && filenameMatch.length > 1) {
            return filenameMatch[1];
          }
        }
        return 'data.xlsx';
      }

    


    ngOnDestroy(): void {
        this.querySub.unsubscribe();
    }

}
