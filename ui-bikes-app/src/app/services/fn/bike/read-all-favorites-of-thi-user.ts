/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFavoritesResponse } from '../../models/page-response-favorites-response';

export interface ReadAllFavoritesOfThiUser$Params {
  page?: number;
  size?: number;
}

export function readAllFavoritesOfThiUser(http: HttpClient, rootUrl: string, params?: ReadAllFavoritesOfThiUser$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFavoritesResponse>> {
  const rb = new RequestBuilder(rootUrl, readAllFavoritesOfThiUser.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFavoritesResponse>;
    })
  );
}

readAllFavoritesOfThiUser.PATH = '/bikes/favorites';