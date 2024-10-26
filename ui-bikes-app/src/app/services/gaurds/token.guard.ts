import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {TokenService} from "../tokenService/token.service";

export const tokenGuard: CanActivateFn = (route, state) => {
  const tokenService = inject(TokenService);
  const router = inject(Router);
  if (!tokenService.tokenValid()) {
    router.navigate(['/login'])
  }
  return true;
};
