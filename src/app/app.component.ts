import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LogInArea } from "./components/log-in-area/log-in-area";
import { Header } from './components/header/header';
import { ListeningArea } from "./components/listening-area/listening-area";
import { UploadWork } from "./components/upload-work/upload-work";
import { BeatDetails } from "./components/details-component/beat-details/beat-details";
import { Admin } from "./components/admin/admin";
import { UserPage } from "./components/user-page/user-page";
import { AddNote } from "./components/notes/add-note/add-note";
import { WorkList } from './components/home-lists/work-list/work-list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`,
  styleUrl: './app.component.css'
})
export class AppComponent {
    title = 'no_saintz_app';
}
