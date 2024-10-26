import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BikeRoutingModule} from './bike-routing.module';
import {DisplayLayoutComponent} from './layout/display-layout/display-layout.component';
import {MenuComponent} from './components/menu/menu.component';
import {AllBikesComponent} from './pages/all-bikes/all-bikes.component';
import {MyBikesComponent} from './pages/my-bikes/my-bikes.component';
import {WaitingListComponent} from './pages/waiting-list/waiting-list.component';
import {BikeCardComponent} from './components/bike-card/bike-card.component';
import {RatingComponent} from './components/rating/rating.component';
import {CommentComponent} from './components/comment/comment.component';
import {BorrowedBikesComponent} from './pages/borrowed-bikes/borrowed-bikes.component';
import {FormsModule} from "@angular/forms";
import {BikeFormComponent} from './pages/bike-form/bike-form.component';
import {FavoritesComponent} from './pages/favorites/favorites.component';
import {BikeDetailsComponent} from './pages/bike-details/bike-details.component';


@NgModule({
  declarations: [
    DisplayLayoutComponent,
    MenuComponent,
    AllBikesComponent,
    MyBikesComponent,
    WaitingListComponent,
    BikeCardComponent,
    RatingComponent,
    CommentComponent,
    BorrowedBikesComponent,
    BikeFormComponent,
    FavoritesComponent,
    BikeDetailsComponent
  ],
  imports: [
    CommonModule,
    BikeRoutingModule,
    FormsModule
  ]
})
export class BikeModule {
}
