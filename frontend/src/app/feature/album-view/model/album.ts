import { AlbumVisibility } from './album-visibility';

export interface Album {
  id: number;
  name: string;
  description: string;
  ownerId: string;
  owner: string;
  visibility: AlbumVisibility;
  updatedAt: Date;
  createdAt: Date;
}

export type NewAlbum = Pick<Album, 'name' | 'description' | 'visibility'>;
