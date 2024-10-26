/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFeedbackResponse } from '../../models/page-response-feedback-response';

export interface ReadAllFeedbacksOfBike$Params {
  page?: number;
  size?: number;
  'bike-id': number;
}

export function readAllFeedbacksOfBike(http: HttpClient, rootUrl: string, params: ReadAllFeedbacksOfBike$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackResponse>> {
  const rb = new RequestBuilder(rootUrl, readAllFeedbacksOfBike.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
    rb.path('bike-id', params['bike-id'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFeedbackResponse>;
    })
  );
}

readAllFeedbacksOfBike.PATH = '/feedbacks/bike/{bike-id}';
