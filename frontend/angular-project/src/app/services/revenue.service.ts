import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment";
import { map } from 'rxjs/operators';


@Injectable({
    providedIn: 'root'
})
export class RevenueService {


    constructor(private http: HttpClient) {
    }

    getTotal(year : number, month : number): Observable<number> {
        const url =`${apiUrl}/seller/revenue/cal?year=${year}&month=${month}`;
        return this.http.get<number>(url);
    }

    showRevenueList(page : number, size : number): Observable<any>{
        const url =`${apiUrl}/seller/revenue/show?page=${page}&size=${size}`;
        return this.http.get(url).pipe(
            map(response => response),
            catchError(error => {
              // Xử lý lỗi nếu có
              console.error('Error:', error);
              return of(null); // Trả về Observable null trong trường hợp lỗi
            })

        );
    }

}
