import {Component, OnInit} from '@angular/core';
import {BikeService} from "../../../../services/services/bike.service";
import {PageResponseFavoritesResponse} from "../../../../services/models/page-response-favorites-response";
import {FavoritesResponse} from "../../../../services/models/favorites-response";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.scss'
})
export class FavoritesComponent implements OnInit {
  page: number = 0;
  size: number = 5;
  favoritesPageRes: PageResponseFavoritesResponse = {}
  wrong = "";
  actionMessage = "";
  actionWitness: boolean = false;

  constructor(private bikeService: BikeService,
              private toasterService: ToastrService) {
  }

  ngOnInit() {
    this.fetchFavorites();
  }

  private fetchFavorites() {
    this.bikeService.readAllFavoritesOfThiUser({
      page: this.page,
      size: this.size
    }).subscribe(
      {
        next: (resp) => {
          this.favoritesPageRes = resp;
        },
        error: (err) => {
          this.wrong = "Something went wrong";
          console.log(err);
        }
      }
    )
  }


  borrowFavBike(fav: FavoritesResponse) {
    this.bikeService.borrowBike({
      "bike-id": fav.bikeId as number
    }).subscribe(
      {
        next: (resp) => {
          fav.borrowed = true;
          this.toasterService.success
          (`Bike ${fav.bikeName} borrowed successfully`, "Done !");
        },
        error: () => {
          this.toasterService.error("Something went wrong", "Owps !");
        }
      }
    )
  }
}
