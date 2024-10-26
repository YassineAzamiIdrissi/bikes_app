import {Component, OnInit} from '@angular/core';
import {BikeService} from "../../../../services/services/bike.service";
import {PageResponseBorrowedBikeResponse} from "../../../../services/models/page-response-borrowed-bike-response";
import {BorrowedBikeResponse} from "../../../../services/models/borrowed-bike-response";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-waiting-list',
  templateUrl: './waiting-list.component.html',
  styleUrl: './waiting-list.component.scss'
})
export class WaitingListComponent implements OnInit {
  page: number = 0;
  size: number = 22;
  returnsPage: PageResponseBorrowedBikeResponse = {}
  actionMessage: string = "";
  actionWitness = false;

  constructor(private bikeService: BikeService,
              private toasterService: ToastrService) {
  }

  ngOnInit(): void {
    this.fetchReturns();
  }

  fetchReturns() {
    this.bikeService.findAllReturnedBikesToConnectedUser(
      {
        page: this.page,
        size: this.size
      }
    ).subscribe({
      next: (resp) => {
        this.returnsPage = resp;
      },
      error: (error) => {
        console.log("An error occurred trying to fetch returns")
        console.log(error);
      }
    })
  }

  acceptReturn(bike: BorrowedBikeResponse) {
    this.bikeService.approveBike(
      {
        "bike-id": bike.id as number
      }
    ).subscribe(
      {
        next: () => {
          bike.accepted = true;
          this.toasterService.success("Bike accepted successfully", "Done !")
        },
        error: (err) => {
          this.toasterService.error("Something went wrong !", "Owps !!");
        }
      }
    )
  }
}
