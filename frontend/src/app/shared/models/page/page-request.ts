import { HttpParams } from '@angular/common/http';
import { Pagination } from './page';

export class PageRequest {
  private constructor(
    private page: number,
    private size: number,
    private sort: string[],
    private direction: Direction
  ) {}

  public static default(): PageRequest {
    return new PageRequest(0, 10, [], Direction.ASC);
  }

  public static first(size = 10): PageRequest {
    return new PageRequest(0, size, [], Direction.ASC);
  }

  public static of(page: number, size: number, sort: string[] = [], direction = Direction.ASC): PageRequest {
    return new PageRequest(page, size, sort, direction);
  }

  public static fromPagination(pagination: Pagination) {
    return PageRequest.of(
      pagination.pageable.pageNumber,
      pagination.pageable.pageSize,
      [pagination.pageable.sort.property],
      pagination.pageable.sort.direction === 'ASC' ? Direction.ASC : Direction.DESC
    );
  }

  public next(): PageRequest {
    return new PageRequest(this.page + 1, this.size, this.sort, this.direction);
  }

  public prev(): PageRequest {
    return new PageRequest(this.page - 1, this.size, this.sort, this.direction);
  }

  public withPage(page: number): PageRequest {
    return new PageRequest(page, this.size, this.sort, this.direction);
  }

  public hasPrevious(): boolean {
    return this.page > 0;
  }

  public toParams(): HttpParams {
    return new HttpParams()
      .set('page', this.page)
      .set('size', this.size)
      .set('sort', this.sort.join(','))
      .set('direction', this.direction);
  }
}

export enum Direction {
  ASC = 'ASC',
  DESC = 'DESC',
}
