/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBikeResponse } from '../../models/page-response-bike-response';

export interface GetMyOwnBikes$Params {
  page?: number;
  size?: number;
}

export function getMyOwnBikes(http: HttpClient, rootUrl: string, params?: GetMyOwnBikes$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBikeResponse>> {
  const rb = new RequestBuilder(rootUrl, getMyOwnBikes.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseBikeResponse>;
    })
  );
}

getMyOwnBikes.PATH = '/bikes/own-bikes';
