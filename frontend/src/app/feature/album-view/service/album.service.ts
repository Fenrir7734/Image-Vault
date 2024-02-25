import { Injectable } from '@angular/core';
import { SpinnerService } from '../../../shared/components/spinner/spinner.service';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Album, CreateAlbum, EditAlbum } from '../model/album';
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

  public createAlbum(album: CreateAlbum): Observable<void> {
    this.loading.start(SpinnerState.LOADING);
    return this.http.post<void>(`${environment.baseUrl}/core/api/albums`, album).pipe(
      map(result => {
        this.loading.stop();
        return result;
      })
    );
  }

  public editAlbum(album: EditAlbum): Observable<void> {
    this.loading.start(SpinnerState.LOADING);
    return this.http.put<void>(`${environment.baseUrl}/core/api/albums`, album).pipe(
      map(result => {
        this.loading.stop();
        return result;
      })
    );
  }
}
