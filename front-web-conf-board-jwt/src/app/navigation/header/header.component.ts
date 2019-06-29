import { Component, OnInit, Input } from '@angular/core';
import { NavService } from 'src/app/shared/navigation/nav.service';
import { DefaultUrlSerializer, Router } from '@angular/router';


@Component({
  selector: 'ak-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {

  @Input() isAuthenticated;

  constructor(public navService: NavService, private router: Router) { }

  public ngOnInit(): void {
    const token = window.localStorage.getItem('token');
    if (token) {
      this.isAuthenticated = true;
      return;
    } else {
        this.isAuthenticated = false;
        return;
      }
   }

  logOut() {
      this.isAuthenticated = false;
      this.router.navigate(['/login']);
    }

}
