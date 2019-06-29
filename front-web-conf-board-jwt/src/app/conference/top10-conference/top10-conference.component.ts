import { Component, OnInit, ViewChild } from '@angular/core';
import { Conference } from 'src/app/shared/models/conference.model';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { ConferenceService } from 'src/app/shared/services/conference.service';
import { Router } from '@angular/router';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';

@Component({
  selector: 'ak-top10-conference',
  templateUrl: './top10-conference.component.html',
  styleUrls: ['./top10-conference.component.scss']
})
export class Top10ConferenceComponent implements OnInit {

  public CONFERENCE_API = 'http://localhost8080/conferences';

  conferences: Conference[];

  public displayedColumns = ['titre', 'expert', 'moyenne', 'niveau', 'consulter', 'editer', 'supprimer'];
  public dataSource = new MatTableDataSource<Conference>();
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor(private conferenceService: ConferenceService, private router: Router, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    this.conferenceService.getTop10ByRate().subscribe(data => {
      if (data) {
        console.log(data);
        this.conferences = data.result;
        this.dataSource.data = this.conferences as Conference[];
        }
      },
      (error) => {
        this.errorService.handleError(error);
      }
    );
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  remove(conference: Conference) {
    conference.href = this.CONFERENCE_API + conference.id;
    this.conferenceService.remove(conference.href).subscribe(result => {
      this.conferenceService.getAll().subscribe(data => {
        this.conferences = data.result;
        this.dataSource.data = this.conferences as Conference[];
      },
      (error) => {
        this.errorService.handleError(error);
        console.error(error);
      }
      );
    },
    (error) => {
      this.errorService.handleError(error);
      console.error(error);
    });
  }

  gotoList() {
    this.router.navigate(['/top10-conference']);
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

}
