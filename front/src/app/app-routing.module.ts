import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { LoginComponent } from './connexion/login/login.component';
import { EditUserComponent } from './user//edit-user/edit-user.component';
import { ListUserComponent } from './user/list-user/list-user.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  //{ path: 'profile', component: ProfileComponent },

  { path: 'gestion', children: [
    { path: 'utilisateur', children: [
      { path: 'list-user', component: ListUserComponent},
      { path: 'add-user', component: EditUserComponent },
      //{ path: 'search-user', component: SearchUserComponent }
      ]
    }/*,
    { path: 'conference', children: [
      { path: 'list-conference', component: ListConferenceComponent},
      { path: 'add-conference', component: EditConferenceComponent },
      { path: 'search-user', component: SearchUserComponent }
      ]
    }*/
  ]
},

  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'add-user', component: EditUserComponent },
  { path: 'edit-user/:id', component: EditUserComponent },
  { path: 'list-user', component: ListUserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
