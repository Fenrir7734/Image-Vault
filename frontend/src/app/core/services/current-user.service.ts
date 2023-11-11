import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { CurrentUser } from '../models/current-user';
import { SpinnerService } from '../../shared/spinner/spinner.service';
import { SpinnerState } from '../../shared/spinner/spinner-state';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CurrentUserService {
  constructor(private loading: SpinnerService, private http: HttpClient) {}

  public fetchCurrentUser(): Observable<CurrentUser> {
    this.loading.start(SpinnerState.FETCHING);
    return this.http.get<CurrentUser>(`${environment.baseUrl}/core/api/users/me`).pipe(
      map(result => {
        this.loading.stop();
        return result;
      })
    );
  }
}
