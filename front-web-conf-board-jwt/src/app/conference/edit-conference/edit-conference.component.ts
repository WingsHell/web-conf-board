import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Conference } from 'src/app/shared/models/conference.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ConferenceService } from 'src/app/shared/services/conference.service';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'ak-edit-conference',
  templateUrl: './edit-conference.component.html',
  styleUrls: ['./edit-conference.component.scss'],
})
export class EditConferenceComponent implements OnInit {

  public CONFERENCE_API = 'http://localhost:8080/conferences/';

  conference: Conference;
  sub: Subscription;
  public displayColumns = ['voted'];

  public selectOptions = [
    {name: 'Novice', value: 'NOVICE'},
    {name: 'Débutant', value: 'DEBUTANT'},
    {name: 'Intermédiaire', value: 'INTERMEDIAIRE'},
    {name: 'Avancé', value: 'AVANCE'},
    {name: 'Expert', value: 'EXPERT'},
  ];

  constructor(private route: ActivatedRoute, private router: Router
            , private conferenceService: ConferenceService, private errorService: ErrorHandlerService) { }

  ngOnInit(): void {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    } else {
      this.sub = this.route.params.subscribe(params => {
        const id = params['id'];

        if (id) {
          this.conferenceService.get(id).subscribe(data => {
            if (data) {
              this.conference = data.result as Conference;
              console.log(this.conference);
              this.conference.href = this.CONFERENCE_API + id;
              } else {
                console.log(`Conference with id '${id}' not found, returning to list`);
                this.gotoList();
              }
          },
          (error) => {
            this.errorService.handleError(error);
          });
        } else {
          this.conference = new Conference();
        }
      },
      (error) => {
        this.errorService.handleError(error);
      });
    }

  }



  gotoList() {
    this.router.navigate(['/list-conference']);
  }

  save(form: NgForm) {
    console.log(form);
    this.conferenceService.save(form).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }

  update(form: NgForm) {
    console.log(form);
    this.conferenceService.update(form).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }

  remove(href) {
    this.conferenceService.remove(href).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }

  onChange(event) {
    console.log(event);
  }
}
