export interface CurrentUser {
  id: number;
  email: string;
  externalName: string;
  name: string;
  verified: boolean;
  enabled: boolean;
  roleId: number;
  role: string;
  updatedAt: Date;
  createdAt: Date;
}
