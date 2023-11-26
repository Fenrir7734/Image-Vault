import { AuthService } from '../services/auth.service';
import { EMPTY, Observable, catchError, throwError } from 'rxjs';
import {
  HttpErrorResponse,
  HttpHandler,
  HttpHeaderResponse,
  HttpInterceptor,
  HttpProgressEvent,
  HttpRequest,
  HttpResponse,
  HttpSentEvent,
  HttpUserEvent,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
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
          }
        }
        return throwError(err);
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
