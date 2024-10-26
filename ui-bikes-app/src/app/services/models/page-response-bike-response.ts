/* tslint:disable */
/* eslint-disable */
import { BikeResponse } from '../models/bike-response';
export interface PageResponseBikeResponse {
  content?: Array<BikeResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
