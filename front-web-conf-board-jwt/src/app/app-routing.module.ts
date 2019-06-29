import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { LoginComponent } from './login/login.component';
import { EditUserComponent } from './user//edit-user/edit-user.component';
import { ListUserComponent } from './user/list-user/list-user.component';
import { ListConferenceComponent } from './conference/list-conference/list-conference.component';
import { EditConferenceComponent } from './conference/edit-conference/edit-conference.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Top10ConferenceComponent } from './conference/top10-conference/top10-conference.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  //{ path: 'profile', component: ProfileComponent },

  { path: 'gestion', children: [
    { path: 'utilisateur', children: [
      { path: 'list-user', component: ListUserComponent},
      { path: 'add-user', component: EditUserComponent },
      ]
    },
    { path: 'conference', children: [
      { path: 'list-conference', component: ListConferenceComponent},
      { path: 'add-conference', component: EditConferenceComponent },
      ]
    }
  ]
},

  { path: 'dashboard', component: DashboardComponent},
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'add-user', component: EditUserComponent },
  { path: 'edit-user/:id', component: EditUserComponent },
  { path: 'list-conference', component: ListConferenceComponent},
  { path: 'add-conference', component: EditConferenceComponent },
  { path: 'edit-conference/:id', component: EditConferenceComponent },
  { path: 'top10-conference', component: Top10ConferenceComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
