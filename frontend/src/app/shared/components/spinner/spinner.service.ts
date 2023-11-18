import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { SpinnerState } from './spinner-state';

@Injectable({
  providedIn: 'root',
})
export class SpinnerService {
  private readonly _loading = new BehaviorSubject<boolean>(false);
  private readonly _state = new BehaviorSubject<SpinnerState>(SpinnerState.NO_TEXT);
  readonly loading$ = this._loading.asObservable();
  readonly state$ = this._state.asObservable();

  private count = 0;

  start(state = SpinnerState.LOADING) {
    this.count++;
    this._loading.next(true);
    this._state.next(state);
  }

  stop() {
    if (this.count > 0) {
      this.count--;
    }
    if (this.count <= 0) {
      this._loading.next(false);
    }
  }
}
