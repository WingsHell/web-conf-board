import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api.response';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public USER_API = '//localhost:8080/users';

  constructor(private http: HttpClient) { }

  getAll(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.USER_API);
  }

  get(id: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.USER_API + '/' + id);
  }

  signUp(user: any): Observable<ApiResponse> {
    console.log('service: ' + JSON.stringify(user));
    return this.http.post<ApiResponse>(this.USER_API + '/sign-up', + user);
  }

  /*save(user: any): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.USER_API, + user);
  }*/

  update(user: any): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.USER_API + '/' + user.id, user);
  }

  remove(href: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(href);
  }
}
