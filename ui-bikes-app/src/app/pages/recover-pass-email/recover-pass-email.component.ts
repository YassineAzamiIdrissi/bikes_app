import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-recover-pass-email',
  templateUrl: './recover-pass-email.component.html',
  styleUrl: './recover-pass-email.component.scss'
})
export class RecoverPassEmailComponent {
  email: string = "";
  errorMessage: string = "";

  constructor(private router: Router,
              private authService: AuthenticationService) {
  }

  sendEmail() {
    console.log("This is the email");
    console.log(this.email);
    this.authService.recoverPass({
      email: this.email
    }).subscribe({
      next: (resp) => {
        this.router.navigate(["setCodeFrom", this.email]);
      },
      error: (err) => {
        this.errorMessage = err.error.message;
        console.log(err);
      }
    })
  }
}
