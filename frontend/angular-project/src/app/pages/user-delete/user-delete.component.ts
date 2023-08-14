import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {Router,ActivatedRoute} from "@angular/router";
import {Observable, Subject} from "rxjs";
import {Role} from "../../enum/Role";

@Component({
  selector: 'user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {
  email: string;
  deleted : boolean;

  

  constructor(private userService: UserService,
    private router: Router,
    private route : ActivatedRoute) {
}

  ngOnInit() {
    this.email = this.route.snapshot.paramMap.get('email');
    this.deleted = false;
}
delete() {
    this.userService.delete(this.email).subscribe(() => {
        },
        (error) => {
            console.error("Error at Delete Product", error);
        });

}
onSubmit() {
    if (this.email) {
        this.delete();
        this.deleted = true;
    } 
    this.router.navigate(['/seller/management']);

}


}
