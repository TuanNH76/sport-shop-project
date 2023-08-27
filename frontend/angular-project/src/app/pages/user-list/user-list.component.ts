import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {JwtResponse} from "../../response/JwtResponse";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from "../../enum/Role";

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

    constructor(private userService: UserService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    Role = Role;
    currentUser: JwtResponse;
    page: any;
    private querySub: Subscription;
    role : string;
    ngOnInit() {
        this.role = this.route.snapshot.paramMap.get('role');
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
        this.userService.getAllByRole(this.role,+page,+size)
            .subscribe(page => {
                this.page = page;
            });

    }

    
}
