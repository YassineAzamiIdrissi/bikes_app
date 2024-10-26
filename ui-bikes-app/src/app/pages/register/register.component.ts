import {Component} from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerRequest: RegistrationRequest = {
    email: "",
    password: "",
    firstname: "",
    lastname: ""
  }
  errorMessage: string = ""
  validationErrors: Array<any> = []

  constructor(private authService: AuthenticationService,
              private router: Router) {
  }


  register() {
    this.authService.registerUser({
      body: this.registerRequest
    }).subscribe({
      next: resp => {
        this.router.navigateByUrl("/activate-account");
      },
      error: err => {
        if (err.status == 400) {
          this.validationErrors = err.error.errors;
        } else {
          this.errorMessage = err.error.message;
        }
      }
    })
  }

  moveToLogin() {
    this.router.navigate(['login']);
  }
}
