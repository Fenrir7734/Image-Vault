import { NgModule } from '@angular/core';
import { AuthComponent } from './components/auth/auth.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';
import { MaterialModule } from '../shared/material.module';
import { SharedModule } from '../shared/shared.module';
import { TranslateComponent } from './components/translate/translate.component';
import { MainComponent } from './components/main/main.component';
import { authGuard } from './guards/auth.guard';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { CoreStateModule } from './state/core-state.module';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { SidenavUserComponent } from './components/sidenav/sidenav-user/sidenav-user.component';
import { SidenavNavigationComponent } from './components/sidenav/sidenav-navigation/sidenav-navigation.component';
import { SidenavControlComponent } from './components/sidenav/sidenav-control/sidenav-control.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';

@NgModule({
  declarations: [
    AuthComponent,
    TranslateComponent,
    MainComponent,
    ToolbarComponent,
    SidenavComponent,
    SidenavUserComponent,
    SidenavNavigationComponent,
    SidenavControlComponent,
  ],
  imports: [
    SharedModule,
    MaterialModule,
    CoreStateModule,
    AppRoutingModule,
    RouterModule.forChild([
      {
        path: '',
        component: MainComponent,
        children: [
          {
            path: '',
            redirectTo: 'home',
            pathMatch: 'full',
          },
          {
            path: 'home',
            loadChildren: () => import('../feature/home-view/home-view.module').then(m => m.HomeViewModule),
            canActivate: [authGuard],
          },
          {
            path: 'albums',
            loadChildren: () => import('../feature/album-view/album-view.module').then(m => m.AlbumViewModule),
            canActivate: [authGuard],
          },
          {
            path: 'gallery',
            loadChildren: () => import('../feature/gallery-view/gallery-view.module').then(m => m.GalleryViewModule),
            canActivate: [authGuard],
          },
          {
            path: 'profile',
            loadChildren: () => import('../feature/profile-view/profile-view.module').then(m => m.ProfileViewModule),
            canActivate: [authGuard],
          },
          {
            path: 'settings',
            loadChildren: () => import('../feature/settings-view/settings-view.module').then(m => m.SettingsViewModule),
            canActivate: [authGuard],
          },
          {
            path: '**',
            redirectTo: 'home',
            pathMatch: 'full',
          },
        ],
      },
    ]),
    ReactiveFormsModule,
  ],
  providers: [MessageService],
})
export class CoreModule {}
