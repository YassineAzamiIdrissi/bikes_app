/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBikeResponse } from '../../models/page-response-bike-response';

export interface GetTimeLineBikes$Params {
  page?: number;
  size?: number;
}

export function getTimeLineBikes(http: HttpClient, rootUrl: string, params?: GetTimeLineBikes$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBikeResponse>> {
  const rb = new RequestBuilder(rootUrl, getTimeLineBikes.PATH, 'get');
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

getTimeLineBikes.PATH = '/bikes';
