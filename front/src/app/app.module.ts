import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// material
import {
  MatButtonModule,
  MatIconModule,
  MatListModule,
  MatSidenavModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
} from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';

import { LoginComponent } from './login/login.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { EditUserComponent } from './user/edit-user/edit-user.component';
import { ListUserComponent } from './user/list-user/list-user.component';
import { SigninComponent } from './signin/signin.component';
import { HeaderComponent } from './navigation/header/header.component';
import { SidenavListComponent } from './navigation/sidenav-list/sidenav-list.component';

/**
 * NgModule that includes all Material modules that are required.
 */
@NgModule({
  exports: [
    // Material
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatSidenavModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
  ],
  declarations: []
})
export class MaterialModule {}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddUserComponent,
    EditUserComponent,
    ListUserComponent,
    SigninComponent,
    HeaderComponent,
    SidenavListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
