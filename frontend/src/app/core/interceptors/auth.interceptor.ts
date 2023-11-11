import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpErrorResponse,
  HttpUserEvent,
  HttpProgressEvent,
  HttpResponse,
  HttpHeaderResponse,
  HttpSentEvent,
} from '@angular/common/http';
import { catchError, EMPTY, Observable, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { JwtService } from '../services/jwt.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private jwtService: JwtService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any> | any> {
    return next.handle(this.addHeadersToRequest(request)).pipe(
      catchError(err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.authService.logout();
            return EMPTY;
          }
          if (err.status === 404 && err.url?.indexOf('api') === -1) {
            return EMPTY;
          }
        } else {
          return throwError(err);
        }

        return EMPTY;
      })
    );
  }

  private addHeadersToRequest(request: HttpRequest<any>) {
    return request.clone({
      withCredentials: true,
      setHeaders: {
        Authorization: `Bearer ${this.jwtService.getAuthToken()}`,
      },
    });
  }
}
