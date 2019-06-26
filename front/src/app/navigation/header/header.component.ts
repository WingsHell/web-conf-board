import { Component, OnInit } from '@angular/core';
import { NavService } from 'src/app/shared/navigation/nav.service';


@Component({
  selector: 'ak-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {

  constructor(public navService: NavService) { }

  public ngOnInit(): void {
  }

}
