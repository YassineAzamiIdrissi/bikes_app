import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BikeService} from "../../../../services/services/bike.service";
import {PageResponseBikeResponse} from "../../../../services/models/page-response-bike-response";
import {BikeResponse} from "../../../../services/models/bike-response";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-my-bikes',
  templateUrl: './my-bikes.component.html',
  styleUrl: './my-bikes.component.scss'
})
export class MyBikesComponent implements OnInit {
  page: number = 0;
  size: number = 3;
  ownBikesPage: PageResponseBikeResponse = {}
  actionMessage: string = "";
  actionWitness: boolean = false;

  constructor(private bikeService: BikeService,
              private router: Router,
              private toasterService: ToastrService) {
    this.fetchMyBikes();
  }

  ngOnInit() {
  }

  fetchMyBikes(): void {
    this.bikeService.getMyOwnBikes(
      {
        page: this.page,
        size: this.size
      }
    ).subscribe(
      {
        next: (resp) => {
          this.ownBikesPage = resp;
        },
        error: (err) => {
          this.toasterService.error
          ("Something went wrong trying to fetch your own bikes !",
            "Owps !!")
          console.log(err);
        }
      }
    )
  }

  handleShare(bike: BikeResponse) {
    this.bikeService.updateShareableStatus(
      {
        'bike-id': bike.id as number
      }
    ).subscribe(
      {
        next: () => {
          console.log("shareable status");
          console.log(bike.shareable);
          bike.shareable = !bike.shareable;
          if (!bike.shareable) {
            this.actionMessage = `Bike ${bike.bikeName} is no longer shareable`;
          } else {
            this.actionMessage = `Bike ${bike.bikeName} is now shareable`;
          }
          this.toasterService.success(this.actionMessage, "Done !")
        },
        error: (err) => {
          this.toasterService.error("Something went wrong", "Owps !");
        }
      }
    );
  }

  handleRepair(bike: BikeResponse) {
    this.bikeService.updateRepairingStatus(
      {
        "bike-id": bike.id as number
      }
    ).subscribe(
      {
        next: () => {
          bike.repairing = !bike.repairing;
          if (!bike.repairing) {
            this.actionMessage = `Bike ${bike.bikeName} is no longer under reparations`;
          } else {
            this.actionMessage = `Bike ${bike.bikeName} is set to reparations`;
          }
          this.toasterService.success(this.actionMessage, "Done");
        },
        error: (err) => {
          this.toasterService.error("Something went wrong", "Owps !!");
          console.log("in handleRepair");
          console.log(err);
        }
      }
    )
  }

  handleEdit(bike: BikeResponse) {
    this.router.navigate(["/bikes/bike-form-edit", bike.id]);
  }
}
