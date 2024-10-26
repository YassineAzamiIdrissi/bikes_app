import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrl: './rating.component.scss'
})
export class RatingComponent {
  @Input()
  rating: number = 0;
  maxRating: number = 5;

  get completeStars() {
    return Math.floor(this.rating);
  }

  get halfStars() {
    return Math.floor(this.rating) != this.rating;
  }

  get emptyStars() {
    return this.maxRating - Math.ceil(this.rating);
  }

}
