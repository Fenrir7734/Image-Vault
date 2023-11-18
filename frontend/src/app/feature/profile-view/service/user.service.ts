import { Injectable } from '@angular/core';
import { SpinnerService } from '../../../shared/components/spinner/spinner.service';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from '../model/user';
import { SpinnerState } from '../../../shared/components/spinner/spinner-state';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private loading: SpinnerService, private http: HttpClient) {}

  public fetchUser(id: number): Observable<User> {
    this.loading.start(SpinnerState.FETCHING);
    return this.http.get<User>(`${environment.baseUrl}/core/api/users/${id}`).pipe(
      map(result => {
        this.loading.stop();
        return result;
      })
    );
  }
}
