import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './pages/login/login.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule, provideHttpClient, withInterceptors, withInterceptorsFromDi} from "@angular/common/http";
import {httpTokenInterceptor} from "./services/interceptors/http-token.interceptor";
import {RegisterComponent} from './pages/register/register.component';
import {ActivateAccountComponent} from './pages/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import {RecoverPassEmailComponent} from './pages/recover-pass-email/recover-pass-email.component';
import {RecoverPassSetCodeComponent} from './pages/recover-pass-set-code/recover-pass-set-code.component';
import {RecoverSetNewPasswordComponent} from './pages/recover-set-new-password/recover-set-new-password.component';
import {ToastrModule} from "ngx-toastr";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    RecoverPassEmailComponent,
    RecoverPassSetCodeComponent,
    RecoverSetNewPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CodeInputModule,
    ToastrModule.forRoot({
      progressBar: true,
      closeButton: true,
      newestOnTop: true,
      tapToDismiss: true,
      positionClass: "toast-bottom-right",
      timeOut: 8000
    })
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi(),
      withInterceptors([httpTokenInterceptor])),
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
