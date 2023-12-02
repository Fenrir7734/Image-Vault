import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Pagination } from '../../models/page/page';
import { Direction, PageRequest } from '../../models/page/page-request';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss'],
})
export class PaginatorComponent {
  @Input() pagination: Pagination | null | undefined;
  @Output() pageChange = new EventEmitter<PageRequest>();

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
