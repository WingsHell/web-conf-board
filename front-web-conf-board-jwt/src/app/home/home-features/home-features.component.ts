import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ConferenceService } from './../../shared/services/conference.service';
import { Conference } from 'src/app/shared/models/conference.model';
import { Subscription } from 'rxjs';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'ak-home-features',
  templateUrl: './home-features.component.html',
  styleUrls: ['./home-features.component.scss']
})
export class HomeFeaturesComponent implements OnInit, AfterViewInit {

  public CONFERENCE_API = 'http://localhost:8080/conferences/';

  conferences: Conference[];

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  public dataSource = new MatTableDataSource<Conference>();
  public displayedColumns = ['accordion'];

  constructor(private conferenceService: ConferenceService, private router: Router, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    } else {
      this.conferenceService.getAll().subscribe(data => {
        if (data) {
          console.log(data.result);
          this.conferences = data.result;
          this.dataSource.data = this.conferences as Conference[];
          console.log(this.dataSource.data);
        }
      },
      (error) => {
        this.errorService.handleError(error);
      });
    }
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

}
