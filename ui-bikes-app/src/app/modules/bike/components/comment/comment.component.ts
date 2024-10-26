import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss'
})
export class CommentComponent {
  @Input()
  own: boolean | undefined = false;
  @Input()
  username: string | undefined = "";
  @Input()
  comment: string | undefined = "";
}
