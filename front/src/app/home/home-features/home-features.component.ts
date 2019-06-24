import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ak-home-features',
  templateUrl: './home-features.component.html',
  styleUrls: ['./home-features.component.scss']
})
export class HomeFeaturesComponent implements OnInit {

  features: any;

  constructor() { }

  ngOnInit() {
    this.features = [
      {
       title: 'Plannifier sa semaine',
       description: 'Visibilité sur les 7 prochains jours',
       icon: 'assets/img/calendar.png'
      },
      {
       title: 'Atteindre ses objectifs',
       description: 'Priorisation des tâches',
       icon: 'assets/img/award.png'
      },
      {
       title: 'Analyser sa productivité',
       description: 'Visualiser le travail accompli',
       icon: 'assets/img/diagram.png'
      }
    ];
  }
}
