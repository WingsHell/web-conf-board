import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ConferenceService } from './../../shared/services/conference.service';
import { Conference } from 'src/app/shared/models/conference.model';
import { Subscription } from 'rxjs';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';

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

  constructor(private conferenceService: ConferenceService, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    this.conferenceService.getAll().subscribe(data => {
      if (data) {
        console.log(data);
        this.conferences = data as Conference[];
        this.dataSource.data = this.conferences as Conference[];
        console.log(this.dataSource.data);
      }
    },
    (error) => {
      this.errorService.handleError(error);
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

}
