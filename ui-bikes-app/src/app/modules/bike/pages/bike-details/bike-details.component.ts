import {Component, OnInit} from '@angular/core';
import {BikeService} from "../../../../services/services/bike.service";
import {BikeResponse} from "../../../../services/models/bike-response";
import {ActivatedRoute} from "@angular/router";
import {Toast, ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-bike-details',
  templateUrl: './bike-details.component.html',
  styleUrl: './bike-details.component.scss'
})
export class BikeDetailsComponent implements OnInit {
  bike: BikeResponse = {}
  bikeId: number = 0;
  error: string = "";
  actionMessage: string = "";
  actionWitness: boolean = false;

  constructor(private bikeService: BikeService,
              private activatedRoute: ActivatedRoute,
              private toasterService: ToastrService) {
  }

  ngOnInit() {
    this.bikeId = this.activatedRoute.snapshot.params["bikeId"];
    console.log("The bike Id from the link");
    this.fetchConcernedBike(this.bikeId);
  }

  fetchConcernedBike(id: number) {
    this.bikeService.getBikeById(
      {
        "bike-id": id
      }
    ).subscribe({
      next: (resp) => {
        this.bike = resp;
        console.log(this.bike);
      },
      error: (err) => {
        this.error = "Something went wrong";
        console.log(err);
      }
    })
  }

  borrowBike() {
    this.bikeService.borrowBike({
      "bike-id": this.bike.id as number
    }).subscribe(
      {
        next: () => {
          this.bike.borrowed = true;
          this.toasterService.success("Borrowed successfully", "Done !");
        },
        error: () => {
          this.toasterService.error("Something went wrong", "Owps !");
        }
      }
    )
  }

  addToFavorite() {
    this.bikeService.addBikeToFavorites(
      {
        'bike-id': this.bike.id as number
      }
    ).subscribe({
      next: (resp) => {
        this.bike.favorite = true;
        this.toasterService.success(`${this.bike.bikeName} added to favorites..`, "Done ");
      },
      error: (err) => {
        this.toasterService.error(`Something went wrong`, "Owps !");
      }
    })
  }
}
