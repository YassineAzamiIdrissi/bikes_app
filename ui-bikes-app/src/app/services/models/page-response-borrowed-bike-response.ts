/* tslint:disable */
/* eslint-disable */
import { BorrowedBikeResponse } from '../models/borrowed-bike-response';
export interface PageResponseBorrowedBikeResponse {
  content?: Array<BorrowedBikeResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
