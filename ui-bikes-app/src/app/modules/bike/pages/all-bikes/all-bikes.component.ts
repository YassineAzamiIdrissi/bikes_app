import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router"
import {BikeService} from "../../../../services/services/bike.service";
import {PageResponseBikeResponse} from "../../../../services/models/page-response-bike-response";
import {BikeResponse} from "../../../../services/models/bike-response";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-all-bikes',
  templateUrl: './all-bikes.component.html',
  styleUrl: './all-bikes.component.scss'
})
export class AllBikesComponent implements OnInit {
  page: number = 0;
  size: number = 3;
  timelineBikes: PageResponseBikeResponse = {}
  loader: boolean = true;

  constructor(private bikeService: BikeService,
              private router: Router,
              private toasterService: ToastrService) {
  }

  ngOnInit(): void {
    this.fetchTimelineBikes();
  }

  fetchTimelineBikes(): void {
    this.loader = true;
    this.bikeService.getTimeLineBikes({
      page: this.page,
      size: this.size
    }).subscribe(
      {
        next: (resp) => {
          this.timelineBikes = resp;
        },
        error: () => {
          console.log("error");
        }
      }
    )
    this.loader = false;
  }

  moveToNext() {
    this.page = this.page + 1;
    this.fetchTimelineBikes();
  }

  moveToLast() {
    this.page = this.timelineBikes.totalPages as number - 1;
    this.fetchTimelineBikes();
  }

  moveToPrevious() {
    this.page = this.page - 1;
    this.fetchTimelineBikes();
  }

  moveToFirst() {
    this.page = 0;
    this.fetchTimelineBikes();
  }

  moveToPage(i: number) {
    this.page = i;
    this.fetchTimelineBikes();
  }

  borrowBike(bike: BikeResponse) {
    this.bikeService.borrowBike(
      {
        "bike-id": bike.id as number,
      }
    ).subscribe({
      next: () => {
        this.toasterService.success("Bike borrowed successfully", "Done");
      },
      error: (error) => {
        this.toasterService.error(error.error.message, "Oops something went wrong !");
      }
    })
  }

  navigateToDetails(bike: BikeResponse) {
    this.router.navigate(["/bikes/bike-details", bike.id])
  }

  favoriteBike(bike: BikeResponse) {
    if (!bike.favorite) {
      this.bikeService.addBikeToFavorites(
        {
          'bike-id': bike.id as number
        }
      ).subscribe({
        next: () => {
          this.toasterService.success(`${bike.bikeName} is now in your favorites`,
            "Done");
        },
        error: (err) => {
          this.toasterService.error(err.error.message, "Ooowps something went wrong");
        }
      })
    } else {
      this.bikeService.deleteBikeFromFavorites(
        {
          'bike-id': bike.id as number
        }
      ).subscribe({
        next: () => {
          this.toasterService.success(`${bike.bikeName} is no longer in your favorites`, "done")
        },
        error: (err) => {
          console.log(err);
          this.toasterService.error("Something isn't alright");
        }
      })
    }
  }
}
