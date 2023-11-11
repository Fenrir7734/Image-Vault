import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { isNil } from 'lodash';
import { Lang } from '../constants/lang';

@Injectable({
  providedIn: 'root',
})
export class LangService {
  private static readonly LANG_KEY = 'lang';
  private static readonly DEFAULT_LANG = Lang.ENGLISH;

  constructor(private translateService: TranslateService) {}

  setLang(): void {
    const preferredLang = this.getPreferredLang();
    const lang =
      !isNil(preferredLang) && this.isSupportedLang(preferredLang) ? preferredLang : LangService.DEFAULT_LANG;

    this.translateService.setDefaultLang(lang);
    this.translateService.use(lang);
  }

  private getPreferredLang(): string | undefined {
    return localStorage.getItem(LangService.LANG_KEY) ?? this.translateService.getBrowserLang();
  }

  private isSupportedLang(lang: string): boolean {
    return Object.values(Lang).some(supportedLang => supportedLang === lang);
  }

  changeLang(lang: string) {
    this.translateService.use(lang);
    localStorage.setItem(LangService.LANG_KEY, lang);
  }
}
