import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  constructor() {
  }

  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token(): string {
    return localStorage.getItem('token') as string;
  }

  tokenValid(): boolean {
    if (!this.token) {
      return false;
    }
    const helper = new JwtHelperService();
    if (helper.isTokenExpired(this.token)) {
      localStorage.removeItem("token");
      return false;
    }
    return true;
  }

  decodePayload(): any {
    const token = this.token;
    if (!token) {
      return null;
    }
    const payload = token.split('.')[1];
    return JSON.parse(atob(payload));
  }
}
