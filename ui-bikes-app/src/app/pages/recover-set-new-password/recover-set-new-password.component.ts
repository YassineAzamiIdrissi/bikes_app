import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {RecoverPasswordRequest} from "../../services/models/recover-password-request";

@Component({
  selector: 'app-recover-set-new-password',
  templateUrl: './recover-set-new-password.component.html',
  styleUrl: './recover-set-new-password.component.scss'
})
export class RecoverSetNewPasswordComponent implements OnInit {
  code: string = "";
  recoverRequest: RecoverPasswordRequest = {password: "", confirmPassword: ""}
  successMessage = "";
  errorMessage: string = "";
  validationErrors: Array<any> = []

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.code = this.activatedRoute.snapshot.queryParams['code'];
  }

  applyPasswordChange() {
    this.validationErrors = [];
    this.errorMessage = "";
    this.authService.setNewPassword({
      recoveryCode: this.code,
      body: this.recoverRequest
    }).subscribe({
      next: (resp) => {
        this.successMessage = resp.message as string;
      },
      error: (err) => {
        console.log(err);
        if (err.status == 400) {
          this.validationErrors = err.error.errors;
        } else {
          this.errorMessage = err.error.message;
        }
      }
    })
  }

  moveToLogin() {
    this.router.navigate(["/login"]);
  }
}
