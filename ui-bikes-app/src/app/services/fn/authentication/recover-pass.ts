/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { MailingRespDto } from '../../models/mailing-resp-dto';

export interface RecoverPass$Params {
  email: string;
}

export function recoverPass(http: HttpClient, rootUrl: string, params: RecoverPass$Params, context?: HttpContext): Observable<StrictHttpResponse<MailingRespDto>> {
  const rb = new RequestBuilder(rootUrl, recoverPass.PATH, 'get');
  if (params) {
    rb.query('email', params.email, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<MailingRespDto>;
    })
  );
}

recoverPass.PATH = '/auth/demand-recover';
