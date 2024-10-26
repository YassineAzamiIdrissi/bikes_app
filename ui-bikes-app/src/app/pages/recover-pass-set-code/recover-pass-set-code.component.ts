import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-recover-pass-set-code',
  templateUrl: './recover-pass-set-code.component.html',
  styleUrl: './recover-pass-set-code.component.scss'
})
export class RecoverPassSetCodeComponent implements OnInit {
  email: string = "";
  code: string = "";
  submitted: boolean = false;
  error: string = "";

  constructor(private activatedRoute: ActivatedRoute,
              private authService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit() {
    this.email = this.activatedRoute.snapshot.params['email'];
    console.log(this.email);
  }

  verifyCode() {
    this.submitted = true;
    this.authService.specifyRecoverCode({
      recoveryCode: this.code
    }).subscribe(
      {
        next: (resp) => {
          // going to password setting...
          this.router.navigate(["setNewPassword"], {
            queryParams: {
              code: this.code
            }
          })
        },
        error: (error) => {
          this.error = error.error.message;
        }
      }
    );
  }
}
