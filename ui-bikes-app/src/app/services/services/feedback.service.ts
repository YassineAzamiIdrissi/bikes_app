/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createdFeedback } from '../fn/feedback/created-feedback';
import { CreatedFeedback$Params } from '../fn/feedback/created-feedback';
import { PageResponseFeedbackResponse } from '../models/page-response-feedback-response';
import { readAllFeedbacksOfBike } from '../fn/feedback/read-all-feedbacks-of-bike';
import { ReadAllFeedbacksOfBike$Params } from '../fn/feedback/read-all-feedbacks-of-bike';

@Injectable({ providedIn: 'root' })
export class FeedbackService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createdFeedback()` */
  static readonly CreatedFeedbackPath = '/feedbacks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createdFeedback()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createdFeedback$Response(params: CreatedFeedback$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return createdFeedback(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createdFeedback$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createdFeedback(params: CreatedFeedback$Params, context?: HttpContext): Observable<number> {
    return this.createdFeedback$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `readAllFeedbacksOfBike()` */
  static readonly ReadAllFeedbacksOfBikePath = '/feedbacks/bike/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `readAllFeedbacksOfBike()` instead.
   *
   * This method doesn't expect any request body.
   */
  readAllFeedbacksOfBike$Response(params: ReadAllFeedbacksOfBike$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackResponse>> {
    return readAllFeedbacksOfBike(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `readAllFeedbacksOfBike$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  readAllFeedbacksOfBike(params: ReadAllFeedbacksOfBike$Params, context?: HttpContext): Observable<PageResponseFeedbackResponse> {
    return this.readAllFeedbacksOfBike$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFeedbackResponse>): PageResponseFeedbackResponse => r.body)
    );
  }

}
