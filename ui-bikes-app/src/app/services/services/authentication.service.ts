/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { activateAccount } from '../fn/authentication/activate-account';
import { ActivateAccount$Params } from '../fn/authentication/activate-account';
import { authenticate } from '../fn/authentication/authenticate';
import { Authenticate$Params } from '../fn/authentication/authenticate';
import { AuthenticationResponse } from '../models/authentication-response';
import { MailingRespDto } from '../models/mailing-resp-dto';
import { recoverPass } from '../fn/authentication/recover-pass';
import { RecoverPass$Params } from '../fn/authentication/recover-pass';
import { registerUser } from '../fn/authentication/register-user';
import { RegisterUser$Params } from '../fn/authentication/register-user';
import { setNewPassword } from '../fn/authentication/set-new-password';
import { SetNewPassword$Params } from '../fn/authentication/set-new-password';
import { specifyRecoverCode } from '../fn/authentication/specify-recover-code';
import { SpecifyRecoverCode$Params } from '../fn/authentication/specify-recover-code';

@Injectable({ providedIn: 'root' })
export class AuthenticationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `registerUser()` */
  static readonly RegisterUserPath = '/auth/register';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `registerUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  registerUser$Response(params: RegisterUser$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return registerUser(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `registerUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  registerUser(params: RegisterUser$Params, context?: HttpContext): Observable<{
}> {
    return this.registerUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `authenticate()` */
  static readonly AuthenticatePath = '/auth/authenticate';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `authenticate()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  authenticate$Response(params: Authenticate$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
    return authenticate(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `authenticate$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  authenticate(params: Authenticate$Params, context?: HttpContext): Observable<AuthenticationResponse> {
    return this.authenticate$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticationResponse>): AuthenticationResponse => r.body)
    );
  }

  /** Path part for operation `specifyRecoverCode()` */
  static readonly SpecifyRecoverCodePath = '/auth/set-recover-code';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `specifyRecoverCode()` instead.
   *
   * This method doesn't expect any request body.
   */
  specifyRecoverCode$Response(params: SpecifyRecoverCode$Params, context?: HttpContext): Observable<StrictHttpResponse<MailingRespDto>> {
    return specifyRecoverCode(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `specifyRecoverCode$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  specifyRecoverCode(params: SpecifyRecoverCode$Params, context?: HttpContext): Observable<MailingRespDto> {
    return this.specifyRecoverCode$Response(params, context).pipe(
      map((r: StrictHttpResponse<MailingRespDto>): MailingRespDto => r.body)
    );
  }

  /** Path part for operation `setNewPassword()` */
  static readonly SetNewPasswordPath = '/auth/set-new-password';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setNewPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setNewPassword$Response(params: SetNewPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<MailingRespDto>> {
    return setNewPassword(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setNewPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setNewPassword(params: SetNewPassword$Params, context?: HttpContext): Observable<MailingRespDto> {
    return this.setNewPassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<MailingRespDto>): MailingRespDto => r.body)
    );
  }

  /** Path part for operation `recoverPass()` */
  static readonly RecoverPassPath = '/auth/demand-recover';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `recoverPass()` instead.
   *
   * This method doesn't expect any request body.
   */
  recoverPass$Response(params: RecoverPass$Params, context?: HttpContext): Observable<StrictHttpResponse<MailingRespDto>> {
    return recoverPass(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `recoverPass$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  recoverPass(params: RecoverPass$Params, context?: HttpContext): Observable<MailingRespDto> {
    return this.recoverPass$Response(params, context).pipe(
      map((r: StrictHttpResponse<MailingRespDto>): MailingRespDto => r.body)
    );
  }

  /** Path part for operation `activateAccount()` */
  static readonly ActivateAccountPath = '/auth/activate-account';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `activateAccount()` instead.
   *
   * This method doesn't expect any request body.
   */
  activateAccount$Response(params: ActivateAccount$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return activateAccount(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `activateAccount$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  activateAccount(params: ActivateAccount$Params, context?: HttpContext): Observable<{
}> {
    return this.activateAccount$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

}
