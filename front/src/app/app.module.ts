import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// cdk
import { A11yModule } from '@angular/cdk/a11y';
import { BidiModule } from '@angular/cdk/bidi';
import { ObserversModule } from '@angular/cdk/observers';
import { OverlayModule } from '@angular/cdk/overlay';
import { PlatformModule } from '@angular/cdk/platform';
import { PortalModule } from '@angular/cdk/portal';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { CdkTableModule } from '@angular/cdk/table';



// material
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatSortModule ,
} from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';

// Component
import { EditUserComponent } from './user/edit-user/edit-user.component';
import { ListUserComponent } from './user/list-user/list-user.component';
import { HeaderComponent } from './navigation/header/header.component';
import { SidenavListComponent } from './navigation/sidenav-list/sidenav-list.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home/home.component';
import { HomeBannerComponent } from './home/home-banner/home-banner.component';
import { HomeFeaturesComponent } from './home/home-features/home-features.component';
import { HomeFeatureCardComponent } from './home/home-feature-card/home-feature-card.component';
import { NotFoundComponent } from './error-pages/not-found/not-found.component';
import { ServerErrorComponent } from './error-pages/server-error/server-error.component';
import { EditConferenceComponent } from './conference/edit-conference/edit-conference.component';
import { ListConferenceComponent } from './conference/list-conference/list-conference.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StarsRatingComponent } from './stars-rating/stars-rating.component';
/**
 * NgModule that includes all Material modules that are required.
 */
@NgModule({
  exports: [
    // CDK
    A11yModule,
    BidiModule,
    ObserversModule,
    OverlayModule,
    PlatformModule,
    PortalModule,
    ScrollingModule,
    CdkStepperModule,
    CdkTableModule,

    // Material
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSliderModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatNativeDateModule,
    MatSortModule,
  ],
  declarations: []
})
export class MaterialModule {}


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    NotFoundComponent,
    ServerErrorComponent,
    EditUserComponent,
    ListUserComponent,
    HeaderComponent,
    SidenavListComponent,
    HomeComponent,
    HomeBannerComponent,
    HomeFeaturesComponent,
    HomeFeatureCardComponent,
    EditConferenceComponent,
    ListConferenceComponent,
    StarsRatingComponent
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
