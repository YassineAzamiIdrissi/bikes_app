import {Component} from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/tokenService/token.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {email: "", password: ""}
  errorMessage: string = ""
  validationErrors: Array<any> = []

  constructor(private authService: AuthenticationService,
              private tokenService: TokenService,
              private router: Router,
              private toasterService: ToastrService)
  {}


  authenticate() {
    this.validationErrors = [];
    this.errorMessage = "";
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: resp => {
        this.tokenService.token = resp.accessToken as string;
        this.router.navigate(["bikes"]);
        // this.toasterService.success("Nicely done", "Success");
      },
      error: err => {
        if (err.status === 400) {
          this.validationErrors = err.error.errors;
        } else {
          this.errorMessage = err.error.message;
        }
      }
    })
  }
}
