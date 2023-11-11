import { Component } from '@angular/core';
import { LangService } from '../../services/lang.service';
import { Lang } from '../../constants/lang';

@Component({
  selector: 'app-translate',
  templateUrl: './translate.component.html',
  styleUrls: ['./translate.component.scss'],
})
export class TranslateComponent {
  supportedLang = Lang;

  constructor(private lang: LangService) {
    this.lang.setLang();
  }

  setLang(lang: Lang) {
    this.lang.changeLang(lang);
  }
}
