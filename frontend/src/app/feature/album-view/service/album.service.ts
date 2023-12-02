import { Injectable } from '@angular/core';
import { SpinnerService } from '../../../shared/components/spinner/spinner.service';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Album } from '../model/album';
import { SpinnerState } from '../../../shared/components/spinner/spinner-state';
import { environment } from '../../../../environments/environment';
import { Page } from '../../../shared/models/page/page';
import { PageRequest } from '../../../shared/models/page/page-request';

@Injectable({
  providedIn: 'root',
})
export class AlbumService {
  constructor(private loading: SpinnerService, private http: HttpClient) {}

  public fetchAlbums(pageRequest: PageRequest): Observable<Page<Album>> {
    this.loading.start(SpinnerState.FETCHING);
    return this.http
      .get<Page<Album>>(`${environment.baseUrl}/core/api/albums`, { params: pageRequest.toParams() })
      .pipe(
        map(result => {
          this.loading.stop();
          return result;
        })
      );
  }
}
