import { Album } from '../model/album';
import { Pagination } from '../../../shared/models/page/page';

export interface AlbumStateModel {
  albums: Album[];
  pagination: Pagination | undefined;
}
