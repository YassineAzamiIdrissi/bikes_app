import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {RecoverPassEmailComponent} from "./pages/recover-pass-email/recover-pass-email.component";
import {RecoverPassSetCodeComponent} from "./pages/recover-pass-set-code/recover-pass-set-code.component";
import {RecoverSetNewPasswordComponent} from "./pages/recover-set-new-password/recover-set-new-password.component";
import {tokenGuard} from "./services/gaurds/token.guard";

const routes: Routes = [
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full"
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "activate-account",
    component: ActivateAccountComponent
  },
  {
    path: "recover-pass-mail",
    component: RecoverPassEmailComponent
  },
  {
    path: "setCodeFrom/:email",
    component: RecoverPassSetCodeComponent
  },
  {
    path: "setNewPassword",
    component: RecoverSetNewPasswordComponent
  },
  {
    path: "bikes",
    loadChildren: () => import("./modules/bike/bike.module").
    then(m => m.BikeModule),
    canActivate: [tokenGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
