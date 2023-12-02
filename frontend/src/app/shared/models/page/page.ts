export interface Page<T> {
  content: T[];
  pageable: Pageable;
  size: number;
  number: number;
  numberOfElements: number;
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
}

export interface Pageable {
  sort: Sort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  paged: boolean;
  unpaged: boolean;
}

export interface Sort {
  sorted: boolean;
  unsorted: boolean;
}
