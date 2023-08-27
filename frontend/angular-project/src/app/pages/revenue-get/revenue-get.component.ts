import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RevenueService } from '../../services/revenue.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-revenue-get',
  templateUrl: './revenue-get.component.html',
  styleUrls: ['./revenue-get.component.css']
})
export class RevenueGetComponent implements OnInit {
  year: number;
  month: number;
  revenue: number;
  show : boolean;
  constructor(
    private route: ActivatedRoute,
    private revenueService: RevenueService
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.year = +params['year'];
      this.month = +params['month'];
    });
    this.show = false;
  }

  calculateRevenue(form: NgForm) {
    if (form.valid) {
      const year = form.value.year;
      const month = form.value.month;
      this.year = year;
      this.month = month;

      // Gọi service để tính toán doanh thu dựa trên year và month
      this.revenueService.getTotal(year, month).subscribe(result => {
        this.revenue=result;
        console.log('Revenue:', this.revenue);
        this.show = true;

        // Xử lý kết quả doanh thu tại đây (hiển thị, lưu vào biến, ...)
      });
    }
  }
}
