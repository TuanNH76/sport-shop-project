import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment";
import { map } from 'rxjs/operators';
import {Review} from '../models/Review';

@Injectable({
    providedIn: 'root'
})

export class ReviewService {
    private reviewUrl = `${apiUrl}/review`

    constructor(private http : HttpClient){

    }

    getAllReviewByProduct(productId : string,page: number, size: number): Observable<any>{
        const url = `${this.reviewUrl}/show/${productId}?page=${page}&size=${size}`
        return this.http.get(url).pipe()
    }

    postReview(review : Review):Observable<Review>{
        const url = `${this.reviewUrl}/post`
        return this.http.post<Review>(url,review);

    }
}