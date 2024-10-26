/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBorrowedBikeResponse } from '../../models/page-response-borrowed-bike-response';

export interface FindAllReturnedBikesToConnectedUser$Params {
  page?: number;
  size?: number;
}

export function findAllReturnedBikesToConnectedUser(http: HttpClient, rootUrl: string, params?: FindAllReturnedBikesToConnectedUser$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBikeResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllReturnedBikesToConnectedUser.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseBorrowedBikeResponse>;
    })
  );
}

findAllReturnedBikesToConnectedUser.PATH = '/bikes/returned';
