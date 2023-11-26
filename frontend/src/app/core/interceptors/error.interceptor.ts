import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';
import { TranslateService } from '@ngx-translate/core';
import { isNil } from 'lodash';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private messageService: MessageService, private translate: TranslateService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError(err => {
        const code = err.error.errorCode;

        if (isNil(code) || !this.hasTranslation(code)) {
          return throwError(err);
        }

        this.messageService.add({
          severity: 'error',
          summary: this.translate.instant('COMMONS.error'),
          detail: this.translate.instant(`ERROR.${code}`),
          sticky: true,
        });

        return throwError(err);
      })
    );
  }

  private hasTranslation(code: string | undefined): boolean {
    if (isNil(code)) return false;
    const translation = this.translate.instant(`ERROR.${code}`);
    return translation !== code && translation !== '';
  }
}
