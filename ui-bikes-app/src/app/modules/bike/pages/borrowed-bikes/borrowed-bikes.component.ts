import {Component, OnInit} from '@angular/core';
import {BikeService} from "../../../../services/services/bike.service";
import {PageResponseBorrowedBikeResponse} from "../../../../services/models/page-response-borrowed-bike-response";
import {BikeResponse} from "../../../../services/models/bike-response";
import {BorrowedBikeResponse} from "../../../../services/models/borrowed-bike-response";
import {FeedbackService} from "../../../../services/services/feedback.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-borrowed-bikes',
  templateUrl: './borrowed-bikes.component.html',
  styleUrl: './borrowed-bikes.component.scss'
})
export class BorrowedBikesComponent implements OnInit {
  page: number = 0;
  size: number = 5;
  borrowedBikesPage: PageResponseBorrowedBikeResponse = {}
  bike: BikeResponse = {};
  mark: number = 0;
  comment: string = "";
  actionMessage: string = "";
  actionWitness: boolean = false;
  validationErrors: Array<any> = [];

  constructor(private bikeService: BikeService,
              private feedbackService: FeedbackService,
              private toasterService: ToastrService) {
  }

  ngOnInit() {
    this.fetchBorrowedBikes();
  }

  fetchBorrowedBikes() {
    this.bikeService.getBorrowedBikes(
      {
        page: this.page,
        size: this.size
      }
    ).subscribe(
      {
        next: (resp) => {
          this.borrowedBikesPage = resp;
        },
        error: (err) => {
          console.log("Something went wrong");
          console.log(err);
        }
      }
    )
  }

  addFeedback(bike: BikeResponse, comment: string, mark: number) {
    this.feedbackService.createdFeedback(
      {
        body: {
          "bikeId": bike.id as number,
          comment,
          mark
        }
      }
    ).subscribe({
      next: () => {
        this.toasterService.success("returned successfully, waiting for the owner's action", "Done !");
      },
      error: (error) => {
        this.toasterService.error("Something went wrong", "Owps !");
      }
    })
  }

  returnBike(bike: BorrowedBikeResponse) {
    this.validationErrors = [];
    this.actionMessage = "";
    this.actionWitness = false;
    this.bikeService.returnBike(
      {
        "bike-id": bike.id as number
      }
    ).subscribe(
      {
        next: () => {
          bike.returned = true;
          this.addFeedback(bike, this.comment, this.mark);
          this.mark = 0;
          this.comment = "";
        },
        error: (error) => {
          this.actionMessage = error.error.message;
          this.actionWitness = false;
          console.log("ERROR IN returnBike");
          console.log(error);
        }
      }
    )
  }

}
