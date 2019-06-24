import { Component, ViewChild, ElementRef, VERSION, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { NavService } from './shared/navigation/nav.service';
import { NavItem } from './shared/navigation/nav-item';

@Component({
  selector: 'ak-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements AfterViewInit {

  @ViewChild('appDrawer', { static: false }) appDrawer: ElementRef;
  version = VERSION;
  navItems: NavItem[] = [
    {
      displayName: 'Client',
      iconName: 'group',
      route: 'client',
      children: [
        {
          displayName: 'Consulter',
          iconName: 'zoom_in',
          route: 'client/consulter',
          children: [
            {
              displayName: 'Liste des clients',
              iconName: 'list',
              route: 'client/consulter/client-list',
            }
          ]
        },
        {
          displayName: 'Création',
          iconName: 'person_add',
          route: 'client/creation',
          children: [
            {
              displayName: 'Ajouter client',
              iconName: 'add',
              route: 'client/creation/client-add',
            }
          ]
        },
        {
          displayName: 'Edition',
          iconName: 'edit',
          route: 'client/edition',
          children: [
            {
              displayName: 'Recherche du client',
              iconName: 'search',
              route: 'client/edition/client-edit/:id',
            }
          ]
        }
      ]
    },
    {
      displayName: 'Contrat',
      iconName: 'description',
      route: 'contrat',
      children: [
        {
          displayName: 'Consulter',
          iconName: 'zoom_in',
          route: 'contrat/consulter',
          children: [
            {
              displayName: 'Liste des contrats',
              iconName: 'list',
              route: 'contrat/consulter/contrat-list',
            }
          ]
        },
        {
          displayName: 'Création',
          iconName: 'note_add',
          route: 'contrat/creation',
          children: [
            {
              displayName: 'Ajouter contrat',
              iconName: 'add',
              route: 'contrat/creation/contrat-add',
            }
          ]
        },
        {
          displayName: 'Edition',
          iconName: 'edit',
          route: 'contrat/edition',
          children: [
            {
              displayName: 'Recherche du contrat',
              iconName: 'search',
              route: 'contrat/edition/contrat-edit/:id',
            }
          ]
        }
      ]
    },
    {
      displayName: 'Sinistre',
      iconName: 'notification_important',
      route: 'sinistre',
      children: [
        {
          displayName: 'Consulter',
          iconName: 'zoom_in',
          route: 'sinistre/consulter',
          children: [
            {
              displayName: 'Liste des sinistres',
              iconName: 'list',
              route: 'sinistre/consulter/sinistre-list',
            }
          ]
        },
        {
          displayName: 'Création',
          iconName: 'add_alert',
          route: 'sinsitre/creation',
          children: [
            {
              displayName: 'Ajouter sinistre',
              iconName: 'add',
              route: 'sinistre/creation/sinistre-add',
            }
          ]
        },
        {
          displayName: 'Edition',
          iconName: 'edit',
          route: 'sinistre/edition',
          children: [
            {
              displayName: 'Recherche du sinistre',
              iconName: 'search',
              route: 'sinistre/edition/sinsitre-edit/:id',
            }
          ]
        }
      ]
    }
  ];

  constructor(private navService: NavService) {}

  ngAfterViewInit() {
    this.navService.appDrawer = this.appDrawer;
  }
}
