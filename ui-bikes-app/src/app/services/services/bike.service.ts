/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addBikeToFavorites } from '../fn/bike/add-bike-to-favorites';
import { AddBikeToFavorites$Params } from '../fn/bike/add-bike-to-favorites';
import { approveBike } from '../fn/bike/approve-bike';
import { ApproveBike$Params } from '../fn/bike/approve-bike';
import { BikeResponse } from '../models/bike-response';
import { borrowBike } from '../fn/bike/borrow-bike';
import { BorrowBike$Params } from '../fn/bike/borrow-bike';
import { deleteBikeFromFavorites } from '../fn/bike/delete-bike-from-favorites';
import { DeleteBikeFromFavorites$Params } from '../fn/bike/delete-bike-from-favorites';
import { findAllReturnedBikesToConnectedUser } from '../fn/bike/find-all-returned-bikes-to-connected-user';
import { FindAllReturnedBikesToConnectedUser$Params } from '../fn/bike/find-all-returned-bikes-to-connected-user';
import { getBikeById } from '../fn/bike/get-bike-by-id';
import { GetBikeById$Params } from '../fn/bike/get-bike-by-id';
import { getBorrowedBikes } from '../fn/bike/get-borrowed-bikes';
import { GetBorrowedBikes$Params } from '../fn/bike/get-borrowed-bikes';
import { getMyOwnBikes } from '../fn/bike/get-my-own-bikes';
import { GetMyOwnBikes$Params } from '../fn/bike/get-my-own-bikes';
import { getTimeLineBikes } from '../fn/bike/get-time-line-bikes';
import { GetTimeLineBikes$Params } from '../fn/bike/get-time-line-bikes';
import { isBikeInFavorites } from '../fn/bike/is-bike-in-favorites';
import { IsBikeInFavorites$Params } from '../fn/bike/is-bike-in-favorites';
import { PageResponseBikeResponse } from '../models/page-response-bike-response';
import { PageResponseBorrowedBikeResponse } from '../models/page-response-borrowed-bike-response';
import { PageResponseFavoritesResponse } from '../models/page-response-favorites-response';
import { readAllFavoritesOfThiUser } from '../fn/bike/read-all-favorites-of-thi-user';
import { ReadAllFavoritesOfThiUser$Params } from '../fn/bike/read-all-favorites-of-thi-user';
import { returnBike } from '../fn/bike/return-bike';
import { ReturnBike$Params } from '../fn/bike/return-bike';
import { saveNewBike } from '../fn/bike/save-new-bike';
import { SaveNewBike$Params } from '../fn/bike/save-new-bike';
import { setBikePicture } from '../fn/bike/set-bike-picture';
import { SetBikePicture$Params } from '../fn/bike/set-bike-picture';
import { updateRepairingStatus } from '../fn/bike/update-repairing-status';
import { UpdateRepairingStatus$Params } from '../fn/bike/update-repairing-status';
import { updateShareableStatus } from '../fn/bike/update-shareable-status';
import { UpdateShareableStatus$Params } from '../fn/bike/update-shareable-status';

@Injectable({ providedIn: 'root' })
export class BikeService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getTimeLineBikes()` */
  static readonly GetTimeLineBikesPath = '/bikes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getTimeLineBikes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getTimeLineBikes$Response(params?: GetTimeLineBikes$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBikeResponse>> {
    return getTimeLineBikes(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getTimeLineBikes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getTimeLineBikes(params?: GetTimeLineBikes$Params, context?: HttpContext): Observable<PageResponseBikeResponse> {
    return this.getTimeLineBikes$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBikeResponse>): PageResponseBikeResponse => r.body)
    );
  }

  /** Path part for operation `saveNewBike()` */
  static readonly SaveNewBikePath = '/bikes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveNewBike()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveNewBike$Response(params: SaveNewBike$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveNewBike(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveNewBike$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveNewBike(params: SaveNewBike$Params, context?: HttpContext): Observable<number> {
    return this.saveNewBike$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `addBikeToFavorites()` */
  static readonly AddBikeToFavoritesPath = '/bikes/favorites/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addBikeToFavorites()` instead.
   *
   * This method doesn't expect any request body.
   */
  addBikeToFavorites$Response(params: AddBikeToFavorites$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return addBikeToFavorites(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addBikeToFavorites$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  addBikeToFavorites(params: AddBikeToFavorites$Params, context?: HttpContext): Observable<number> {
    return this.addBikeToFavorites$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `deleteBikeFromFavorites()` */
  static readonly DeleteBikeFromFavoritesPath = '/bikes/favorites/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteBikeFromFavorites()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteBikeFromFavorites$Response(params: DeleteBikeFromFavorites$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return deleteBikeFromFavorites(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteBikeFromFavorites$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteBikeFromFavorites(params: DeleteBikeFromFavorites$Params, context?: HttpContext): Observable<{
}> {
    return this.deleteBikeFromFavorites$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowBike()` */
  static readonly BorrowBikePath = '/bikes/borrow/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowBike()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBike$Response(params: BorrowBike$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowBike(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowBike$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBike(params: BorrowBike$Params, context?: HttpContext): Observable<number> {
    return this.borrowBike$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateShareableStatus()` */
  static readonly UpdateShareableStatusPath = '/bikes/shareable/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateShareableStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus$Response(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateShareableStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateShareableStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateShareableStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnBike()` */
  static readonly ReturnBikePath = '/bikes/return/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBike()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBike$Response(params: ReturnBike$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnBike(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBike$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBike(params: ReturnBike$Params, context?: HttpContext): Observable<number> {
    return this.returnBike$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateRepairingStatus()` */
  static readonly UpdateRepairingStatusPath = '/bikes/repairing/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateRepairingStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateRepairingStatus$Response(params: UpdateRepairingStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateRepairingStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateRepairingStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateRepairingStatus(params: UpdateRepairingStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateRepairingStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `setBikePicture()` */
  static readonly SetBikePicturePath = '/bikes/picture/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setBikePicture()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  setBikePicture$Response(params: SetBikePicture$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return setBikePicture(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setBikePicture$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  setBikePicture(params: SetBikePicture$Params, context?: HttpContext): Observable<number> {
    return this.setBikePicture$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approveBike()` */
  static readonly ApproveBikePath = '/bikes/approve/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveBike()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveBike$Response(params: ApproveBike$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approveBike(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveBike$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveBike(params: ApproveBike$Params, context?: HttpContext): Observable<number> {
    return this.approveBike$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getBikeById()` */
  static readonly GetBikeByIdPath = '/bikes/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getBikeById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBikeById$Response(params: GetBikeById$Params, context?: HttpContext): Observable<StrictHttpResponse<BikeResponse>> {
    return getBikeById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getBikeById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBikeById(params: GetBikeById$Params, context?: HttpContext): Observable<BikeResponse> {
    return this.getBikeById$Response(params, context).pipe(
      map((r: StrictHttpResponse<BikeResponse>): BikeResponse => r.body)
    );
  }

  /** Path part for operation `findAllReturnedBikesToConnectedUser()` */
  static readonly FindAllReturnedBikesToConnectedUserPath = '/bikes/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReturnedBikesToConnectedUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBikesToConnectedUser$Response(params?: FindAllReturnedBikesToConnectedUser$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBikeResponse>> {
    return findAllReturnedBikesToConnectedUser(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReturnedBikesToConnectedUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBikesToConnectedUser(params?: FindAllReturnedBikesToConnectedUser$Params, context?: HttpContext): Observable<PageResponseBorrowedBikeResponse> {
    return this.findAllReturnedBikesToConnectedUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBikeResponse>): PageResponseBorrowedBikeResponse => r.body)
    );
  }

  /** Path part for operation `getMyOwnBikes()` */
  static readonly GetMyOwnBikesPath = '/bikes/own-bikes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getMyOwnBikes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getMyOwnBikes$Response(params?: GetMyOwnBikes$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBikeResponse>> {
    return getMyOwnBikes(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getMyOwnBikes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getMyOwnBikes(params?: GetMyOwnBikes$Params, context?: HttpContext): Observable<PageResponseBikeResponse> {
    return this.getMyOwnBikes$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBikeResponse>): PageResponseBikeResponse => r.body)
    );
  }

  /** Path part for operation `readAllFavoritesOfThiUser()` */
  static readonly ReadAllFavoritesOfThiUserPath = '/bikes/favorites';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `readAllFavoritesOfThiUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  readAllFavoritesOfThiUser$Response(params?: ReadAllFavoritesOfThiUser$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFavoritesResponse>> {
    return readAllFavoritesOfThiUser(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `readAllFavoritesOfThiUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  readAllFavoritesOfThiUser(params?: ReadAllFavoritesOfThiUser$Params, context?: HttpContext): Observable<PageResponseFavoritesResponse> {
    return this.readAllFavoritesOfThiUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFavoritesResponse>): PageResponseFavoritesResponse => r.body)
    );
  }

  /** Path part for operation `isBikeInFavorites()` */
  static readonly IsBikeInFavoritesPath = '/bikes/favorites/belongs/{bike-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isBikeInFavorites()` instead.
   *
   * This method doesn't expect any request body.
   */
  isBikeInFavorites$Response(params: IsBikeInFavorites$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isBikeInFavorites(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isBikeInFavorites$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isBikeInFavorites(params: IsBikeInFavorites$Params, context?: HttpContext): Observable<boolean> {
    return this.isBikeInFavorites$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `getBorrowedBikes()` */
  static readonly GetBorrowedBikesPath = '/bikes/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getBorrowedBikes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBorrowedBikes$Response(params?: GetBorrowedBikes$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBikeResponse>> {
    return getBorrowedBikes(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getBorrowedBikes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBorrowedBikes(params?: GetBorrowedBikes$Params, context?: HttpContext): Observable<PageResponseBorrowedBikeResponse> {
    return this.getBorrowedBikes$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBikeResponse>): PageResponseBorrowedBikeResponse => r.body)
    );
  }

}
