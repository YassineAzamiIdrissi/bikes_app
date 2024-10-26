/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { MailingRespDto } from '../../models/mailing-resp-dto';
import { RecoverPasswordRequest } from '../../models/recover-password-request';

export interface SetNewPassword$Params {
  recoveryCode: string;
      body: RecoverPasswordRequest
}

export function setNewPassword(http: HttpClient, rootUrl: string, params: SetNewPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<MailingRespDto>> {
  const rb = new RequestBuilder(rootUrl, setNewPassword.PATH, 'patch');
  if (params) {
    rb.query('recoveryCode', params.recoveryCode, {});
    rb.body(params.body, 'application/json');
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

setNewPassword.PATH = '/auth/set-new-password';
