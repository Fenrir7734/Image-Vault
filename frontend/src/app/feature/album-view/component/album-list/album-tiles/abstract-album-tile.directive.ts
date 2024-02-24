import { Directive, Input } from '@angular/core';
import { Album } from '../../../model/album';
import { MenuItem } from 'primeng/api';
import { faEllipsisVertical, faFolder, faUsers } from '@fortawesome/free-solid-svg-icons';
import { AlbumVisibility } from '../../../model/album-visibility';
import { TranslateService } from '@ngx-translate/core';

@Directive()
export abstract class AbstractAlbumTile {
  @Input() album: Album;
  items: MenuItem[];

  albumIcon = faFolder;
  ellipsisIcon = faEllipsisVertical;
  usersIcon = faUsers;

  constructor(private translate: TranslateService) {
    this.initMenuItems();
  }

  isPublic() {
    return this.album.visibility === AlbumVisibility.PUBLIC_RO || this.album.visibility === AlbumVisibility.PUBLIC_RW;
  }

  navigateToAlbum() {}

  initMenuItems() {
    this.items = [
      {
        label: this.translationOf('download'),
        icon: 'pi pi-download',
      },
      {
        label: this.translationOf('rename'),
        icon: 'pi pi-pencil',
      },
      {
        label: this.translationOf('share'),
        icon: 'pi pi-share-alt',
      },
      {
        separator: true,
      },
      {
        label: this.translationOf('remove'),
        icon: 'pi pi-trash',
      },
    ];
  }

  private translationOf(postfix: string): string {
    return this.translate.instant(`ALBUM-VIEW.ACTIONS.${postfix}`);
  }
}
