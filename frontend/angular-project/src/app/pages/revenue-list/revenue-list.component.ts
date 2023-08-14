import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {JwtResponse} from "../../response/JwtResponse";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {Role} from "../../enum/Role";
import {RevenueService} from '../../services/revenue.service'

@Component({
    selector: 'app-revenue-list',
    templateUrl: './revenue-list.component.html',
    styleUrls: ['./revenue-list.component.css']
})
export class RevenueListComponent implements OnInit, OnDestroy {

    constructor(
                private revenueService : RevenueService,
                private route: ActivatedRoute) {
    }

   
    page: any;
    private querySub: Subscription;

    ngOnInit() {
        this.querySub = this.route.queryParams.subscribe(() => {
            this.update();
        });

        
    }

    ngOnDestroy(): void {
        this.querySub.unsubscribe();
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

    getProds(page: number = 1, size: number = 5) {
        this.revenueService.showRevenueList(+page, +size)
            .subscribe(page => {
                this.page = page;
            });

    }

}
