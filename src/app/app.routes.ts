import { HttpClient } from '@angular/common/http';
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserPage } from './components/user-page/user-page';
import { LogInArea } from './components/log-in-area/log-in-area';
import { LogOut } from './components/log-out/log-out';
import { RegisterAreaComponent } from './components/register-area/register-area';
import { Admin } from './components/admin/admin';
import { BeatDetails } from './components/details-component/beat-details/beat-details';
import { WorkDetails } from './components/details-component/work-details/work-details';
import { ListeningArea } from './components/listening-area/listening-area';
import { UploadWork } from './components/upload-work/upload-work';

export const routes: Routes = [{ path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'user', component: UserPage },
    { path: 'user/:id', component: UserPage },
    { path: 'auth/login', component: LogInArea },
    { path: 'auth/logout', component: LogOut },
    { path: 'auth/register', component: RegisterAreaComponent },
    { path: 'admin', component: Admin },
    { path: 'beat-details/:id', component: BeatDetails },
    { path: 'work-details/:id', component: WorkDetails},
    { path: 'work-details/listening-area', component: ListeningArea},
    { path: 'beat-details/listening-area', component: ListeningArea},
    { path: 'upload-work', component: UploadWork}
];
