import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {JwtResponse} from "../../response/JwtResponse";
import {Router} from "@angular/router";
import {Role} from "../../enum/Role";

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit, OnDestroy {


    currentUserSubscription: Subscription;
    name$;
    name: string;
    currentUser: JwtResponse;
    root = '/';
    Role = Role;
    banner : boolean;
    constructor(private userService: UserService,
                private router: Router,
    ) {

    }


    ngOnInit() {
        this.name$ = this.userService.name$.subscribe(aName => this.name = aName);
        this.currentUserSubscription = this.userService.currentUser.subscribe(user => {
            this.currentUser = user;
            if (!user ) {
                this.root = '/';
                this.banner = true;
            }else if(user.role == Role.Customer){
                this.root = '/';
                this.banner = false;
            } 
            else {
                this.root = '/seller';
                this.banner = false;
            }
        });
    }

    ngOnDestroy(): void {
        this.currentUserSubscription.unsubscribe();
        // this.name$.unsubscribe();
    }

    logout() {
        this.userService.logout();
        // this.router.navigate(['/login'], {queryParams: {logout: 'true'}} );
    }

}