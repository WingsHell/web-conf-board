import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { User } from 'src/app/shared/models/user.model';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';
import { ErrorHandlerService } from 'src/app/shared/services/error-handler.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'ak-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent implements OnInit, AfterViewInit {

  public USER_API = 'http://localhost:8080/users/';

  users: User[];

  public displayedColumns = ['nom', 'prenom', 'username', 'email', 'consulter', 'editer', 'supprimer'];
  public dataSource = new MatTableDataSource<User>();
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor(private userService: UserService, private router: Router, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      if (data) {
        console.log(data);
        this.users = data.result;
        this.dataSource.data = this.users as User[];
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

  remove(user: User) {
    user.href = this.USER_API + user.id;
    this.userService.remove(user.href).subscribe(result => {
      this.userService.getAll().subscribe(data => {
        this.users = data.result;
        this.dataSource.data = this.users as User[];
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
    this.router.navigate(['/client-list']);
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

}
