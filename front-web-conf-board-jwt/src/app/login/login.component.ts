import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../shared/models/user.model';
import { NgForm } from '@angular/forms';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { AuthService } from '../shared/services/auth-service.service';

@Component({
  selector: 'ak-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  isAuthenticated: boolean;
  @Output() valueChange = new EventEmitter();

  hide = true;
  invalidLogin: boolean = false;

  user: User;

  constructor(private router: Router, private authService: AuthService, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    window.localStorage.removeItem('token');
  }

  save(form: NgForm) {
    console.log(form);
    this.authService.login(form).subscribe(data => {
      if (data.status === 200) {
        window.localStorage.setItem('token', data.result.token);
        this.isAuthenticated = true;
        this.valueChange.emit(this.isAuthenticated);
        this.router.navigate(['home']);
      } else {
        this.invalidLogin = true;
        alert(data.message);
      }
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });
  }

/*this.userService.save(form).subscribe(result => {
      this.gotoList();
    },
    (error) => {
      console.error(error);
      this.errorService.handleError(error);
    });*/


}


