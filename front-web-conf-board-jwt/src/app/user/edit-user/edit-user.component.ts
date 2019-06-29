import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from 'src/app/shared/models/user.model';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { UserService } from 'src/app/shared/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from './../../shared/services/auth-service.service';

@Component({
  selector: 'ak-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
})
export class EditUserComponent implements OnInit, OnDestroy {

  public USER_API = 'http://localhost:8080/users/';

  hide = true;
  updateLoaded = false;

  user: User;
  sub: Subscription;
  public displayedColumns = ['nom'];

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService
            , private userService: UserService, private errorService: ErrorHandlerService) { }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];

      if (id) {
        this.userService.get(id).subscribe(data => {
          if (data) {
            this.user = data.result;
            console.log(this.user);
            this.user.href = this.USER_API + id;
            this.updateLoaded = true;
            } else {
              console.log(`User with id '${id}' not found, returning to list`);
              this.gotoList();
            }
        },
        (error) => {
          this.errorService.handleError(error);
        });
      } else {
        this.user = new User();
      }
    },
    (error) => {
      this.errorService.handleError(error);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/list-user']);
  }

  save(form: NgForm) {
    console.log(form);
    this.userService.signUp(form).subscribe(data => {
      if (data) {
        this.gotoList();
      }
    },
      (error) => {
        console.error(error);
        this.errorService.handleError(error);
      });
    }
    /*this.userService.signUp(form).subscribe(data => {
      if (data.status === 200) {
        window.localStorage.setItem('token', data.result.token);
        this.router.navigate(['home']);
      } else {
        alert(data.message);
      }
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }*/

  updateUser(form: NgForm) {
    console.log(form);
    this.userService.update(form).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }

  remove(href) {
    this.userService.remove(href).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }



}
