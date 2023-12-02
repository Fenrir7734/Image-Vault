export interface Page<T> {
  content: T[];
  pageable: Pageable;
  size: number;
  sort: Sort[];
  number: number;
  numberOfElements: number;
  totalPages: number;
  totalElements: number;
  empty: boolean;
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
  direction: string;
  property: string;
}

export type Pagination = Omit<Page<any>, 'content'>;
