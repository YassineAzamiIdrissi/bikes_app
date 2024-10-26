import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DisplayLayoutComponent} from "./layout/display-layout/display-layout.component";
import {AllBikesComponent} from "./pages/all-bikes/all-bikes.component";
import {MyBikesComponent} from "./pages/my-bikes/my-bikes.component";
import {WaitingListComponent} from "./pages/waiting-list/waiting-list.component";
import {BorrowedBikesComponent} from "./pages/borrowed-bikes/borrowed-bikes.component";
import {BikeFormComponent} from "./pages/bike-form/bike-form.component";
import {FavoritesComponent} from "./pages/favorites/favorites.component";
import {BikeDetailsComponent} from "./pages/bike-details/bike-details.component";
import {tokenGuard} from "../../services/gaurds/token.guard";

const routes: Routes = [
  {
    path: "",
    component: DisplayLayoutComponent,
    children: [
      {
        path: "",
        component: AllBikesComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "mine",
        component: MyBikesComponent,
        canActivate: [tokenGuard],
      },
      {
        path: "unapproved",
        component: WaitingListComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "borrowed",
        component: BorrowedBikesComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "bike-form",
        component: BikeFormComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "bike-form-edit/:bikeId",
        component: BikeFormComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "favorites",
        component: FavoritesComponent,
        canActivate: [tokenGuard]
      },
      {
        path: "bike-details/:bikeId",
        component: BikeDetailsComponent,
        canActivate: [tokenGuard]
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BikeRoutingModule {
}
