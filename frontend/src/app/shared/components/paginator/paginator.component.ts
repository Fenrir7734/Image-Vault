import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Pagination } from '../../models/page/page';
import { Direction, PageRequest } from '../../models/page/page-request';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss'],
})
export class PaginatorComponent implements OnInit {
  @Input() pagination: Pagination | null | undefined;
  @Output() pageChange = new EventEmitter<PageRequest>();
  first: number;

  ngOnInit() {
    if (this.pagination) {
      this.first = this.pagination.size * this.pagination.pageable.pageNumber;
    } else {
      this.first = 0;
    }
  }

  onPageChange(event: any) {
    if (!this.pagination) {
      this.pageChange.emit(PageRequest.default());
      return;
    }

    const pageRequest = PageRequest.of(
      event.page,
      this.pagination.size,
      [this.pagination.sort[0]?.property],
      this.pagination.sort[0]?.direction === 'ASC' ? Direction.ASC : Direction.DESC
    );
    this.pageChange.emit(pageRequest);
  }
}
