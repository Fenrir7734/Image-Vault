import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { SpinnerService } from './spinner.service';
import { SpinnerState } from './spinner-state';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.scss'],
})
export class SpinnerComponent implements OnInit {
  loading = false;
  state = SpinnerState.NO_TEXT;

  constructor(public spinnerService: SpinnerService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.listenLoadingChange();
    this.listenStateChange();
  }

  private listenLoadingChange() {
    this.spinnerService.loading$.subscribe(loading => {
      this.loading = loading;
      this.cdr.detectChanges();
    });
  }

  private listenStateChange() {
    this.spinnerService.state$.subscribe(state => {
      this.state = state;
      this.cdr.detectChanges();
    });
  }

  get text() {
    switch (this.state) {
      case SpinnerState.FETCHING:
        return 'SPINNER.fetching';
      case SpinnerState.LOADING:
        return 'SPINNER.sending';
      default:
        return '';
    }
  }
}
