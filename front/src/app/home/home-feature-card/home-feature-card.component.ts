import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'ak-home-feature-card',
  templateUrl: './home-feature-card.component.html',
  styleUrls: ['./home-feature-card.component.scss']
})
export class HomeFeatureCardComponent implements OnInit {

  @Input() description: string;
  @Input() icon: string;
  @Input() title: string;

  constructor() { }

  ngOnInit() {
  }

}
