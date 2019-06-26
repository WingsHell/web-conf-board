import { Component, OnInit, Input } from '@angular/core';
import { Conference } from '../../shared/models/conference.model';


@Component({
  selector: 'ak-home-feature-card',
  templateUrl: './home-feature-card.component.html',
  styleUrls: ['./home-feature-card.component.scss']
})
export class HomeFeatureCardComponent implements OnInit {

  @Input() public conference: Conference;

  constructor() { }

  ngOnInit() {
  }

}
