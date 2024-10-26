/* tslint:disable */
/* eslint-disable */
import { FavoritesResponse } from '../models/favorites-response';
export interface PageResponseFavoritesResponse {
  content?: Array<FavoritesResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
