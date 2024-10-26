import {Component} from '@angular/core';
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {
  constructor(private authService: AuthenticationService,
              private router: Router) {
  }

  submitted: boolean = false;
  isOkay: boolean = false;
  message: string = "";

  verifyActivationCode(activationCode: string) {
    this.submitted = true;
    this.authService.activateAccount(
      {
        code: activationCode
      }
    ).subscribe({
      next: (resp) => {
        this.isOkay = true;
        this.message = "Successfully activated, follow this link to login now : "
      },
      error: () => {
        this.isOkay = false;
        this.message = "Something went wrong try again : "
      }
    })
  }

  moveToLogin() {
    this.router.navigate(['login']);
  }
}
