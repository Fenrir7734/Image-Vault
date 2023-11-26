import { Injectable } from '@angular/core';
import { UserPermissions } from '../models/user-permissions.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { isEmpty } from 'lodash';
import { Observable, Subscription, of, map } from 'rxjs';
import { Roles } from '../constants/roles';
import { environment } from '../../../environments/environment';
import { JwtService } from './jwt.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private static readonly X_TOKEN = 'X-Token';

  private _permissions: UserPermissions | null = null;
  private _isLoadingPermissions = false;

  constructor(private httpClient: HttpClient, private jwtService: JwtService, private router: Router) {}

  get userId(): number | undefined {
    return this._permissions?.userId;
  }

  get userRoles(): string[] | undefined {
    return this._permissions?.roles;
  }

  get isLoggedIn(): boolean {
    return !!this.userId;
  }

  get isUser(): boolean {
    return this.isAuthorized() && this.hasAnyRole(Roles.USER, Roles.ADMIN);
  }

  get isAdmin(): boolean {
    return this.isAuthorized() && this.hasAnyRole(Roles.ADMIN);
  }

  private isAuthorized(): boolean {
    return this.isLoggedIn && !isEmpty(this.userRoles);
  }

  private hasAnyRole(...roles: string[]): boolean {
    return !isEmpty(this.userRoles) && roles.some(role => this.userRoles!.indexOf(role) >= 0);
  }

  get isLoadingPermissions(): boolean {
    return this._isLoadingPermissions;
  }

  fetchPermissions(): Subscription {
    this._isLoadingPermissions = true;
    this._permissions = this.jwtService.getUserPermissions();
    if (this._permissions) return of(this._permissions).subscribe();
    return this.exchange().subscribe((res: HttpResponse<Response>) => this.processToken(res));
  }

  private exchange(): Observable<HttpResponse<Response>> {
    return this.httpClient.get<Response>(`${environment.baseUrl}/auth/api/oauth/exchange`, { observe: 'response' });
  }

  login(email: string, password: string): Observable<void> {
    return this.httpClient
      .post<Response>(
        `${environment.baseUrl}/auth/api/standard/exchange`,
        {
          email,
          password,
        },
        { observe: 'response' }
      )
      .pipe(map(res => this.processToken(res)));
  }

  private processToken(res: HttpResponse<Response>) {
    this.jwtService.setAuthToken(res.headers.get(AuthService.X_TOKEN));
    this._permissions = this.jwtService.getUserPermissions();
    this._isLoadingPermissions = false;
  }

  redirectToGoogleLogin(): void {
    window.location.href = environment.googleLoginUrl;
  }

  redirectToFacebookLogin(): void {
    window.location.href = environment.facebookLoginUrl;
  }

  logout(): void {
    this._permissions = null;
    this.jwtService.removeAuthToken();
    this.router.navigate(['login']);
  }
}
