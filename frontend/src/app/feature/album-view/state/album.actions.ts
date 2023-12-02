import { NewAlbum } from '../model/album';
import { PageRequest } from '../../../shared/models/page/page-request';

export namespace AlbumStateActions {
  export class GetPage {
    static readonly type = '[Album] Get album page';

    constructor(public pageRequest: PageRequest) {}
  }

  export class Create {
    static readonly type = '[Album] Create album';

    constructor(public album: NewAlbum) {}
  }
}
