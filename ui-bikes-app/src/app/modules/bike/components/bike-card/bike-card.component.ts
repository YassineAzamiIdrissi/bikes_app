import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BikeResponse} from "../../../../services/models/bike-response";
import {FeedbackService} from "../../../../services/services/feedback.service";
import {PageResponseFeedbackResponse} from "../../../../services/models/page-response-feedback-response";

@Component({
  selector: 'app-bike-card',
  templateUrl: './bike-card.component.html',
  styleUrl: './bike-card.component.scss'
})
export class BikeCardComponent implements OnInit {
  page: number = 0;
  size: number = 5;
  commentsPage: PageResponseFeedbackResponse = {};
  @Input()
  explicitDescription: string = ""

  constructor(private feedbackService: FeedbackService) {
  }

  ngOnInit() {
    this.fetchBikeFeedbacks();
  }

  fetchBikeFeedbacks() {
    this.feedbackService.readAllFeedbacksOfBike({
      page: this.page,
      size: this.size,
      "bike-id": this._bike.id as number
    }).subscribe({
      next: (resp) => {
        this.commentsPage = resp;
      },
      error: (err) => {
        console.error("AN ERROR OCCURRED CHECK CONSOLE : ");
        console.log(err);
      }
    })
  }

  _bike: BikeResponse = {}
  _manage: boolean = false;
  _bikePicture: string = "";
  // events :
  @Output()
  borrow: EventEmitter<BikeResponse> = new EventEmitter();
  @Output()
  favorite: EventEmitter<BikeResponse> = new EventEmitter();
  @Output()
  details: EventEmitter<BikeResponse> = new EventEmitter();
  @Output()
  share: EventEmitter<BikeResponse> = new EventEmitter();
  @Output()
  edit: EventEmitter<BikeResponse> = new EventEmitter();
  @Output()
  repair: EventEmitter<BikeResponse> = new EventEmitter();

  onRepair() {
    this.repair.emit(this._bike);
  }

  onBorrow() {
    this.borrow.emit(this._bike);
  }

  onFavorite() {
    this.favorite.emit(this._bike);
  }

  onShare() {
    this.share.emit(this._bike);
  }

  onEdit() {
    this.edit.emit(this._bike);
  }

  onDetails() {
    this.details.emit(this._bike);
  }

  get manage() {
    return this._manage;
  }

  get bike() {
    return this._bike;
  }

  get bikePicture(): string | undefined {
    if (this._bike.picture) {
      return "data:image/jpg;base64, " + this._bike.picture;
    }
    return "https://thumbs.dreamstime.com/b/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available-236105299.jpg";
  }

  set bikePicture(value: string) {
    this._bikePicture = value;
  }


  @Input()
  set manage(value: boolean) {
    this._manage = value;
  }

  @Input()
  set bike(value: BikeResponse) {
    this._bike = value;
  }

  moveToFirst() {
    this.page = 0;
    this.fetchBikeFeedbacks();
  }

  moveToPage(i: number) {
    this.page = i;
    this.fetchBikeFeedbacks();
  }

  moveToNext() {
    this.page++;
    this.fetchBikeFeedbacks();
  }

  moveToLast() {
    this.page = this.commentsPage.totalPages as number - 1;
    this.fetchBikeFeedbacks();
  }

  moveToPrevious() {
    this.page--;
    this.fetchBikeFeedbacks();
  }
}
