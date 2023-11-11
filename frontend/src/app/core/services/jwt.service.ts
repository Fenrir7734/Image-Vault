import { Injectable } from '@angular/core';
import { UserPermissions } from '../models/user-permissions.model';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class JwtService {
  private static readonly AUTH_TOKEN = 'AuthToken';
  private static readonly BEARER_PREFIX = 'Bearer ';

  public getUserPermissions(): UserPermissions | null {
    const authToken = this.getAuthToken();
    if (!authToken) return null;
    return jwt_decode<UserPermissions>(authToken);
  }

  public setAuthToken(authToken: string | null): void {
    if (!authToken) {
      this.removeAuthToken();
    } else {
      authToken = this.stripAuthToken(authToken);
      localStorage.setItem(JwtService.AUTH_TOKEN, authToken);
    }
  }

  public removeAuthToken(): void {
    localStorage.removeItem(JwtService.AUTH_TOKEN);
  }

  private stripAuthToken(authToken: string): string {
    return authToken.startsWith(JwtService.BEARER_PREFIX)
      ? authToken.slice(JwtService.BEARER_PREFIX.length)
      : authToken;
  }

  public getAuthToken(): string | null {
    return localStorage.getItem(JwtService.AUTH_TOKEN);
  }
}
