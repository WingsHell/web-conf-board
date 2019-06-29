import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api.response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public API = '//localhost:8080/';


  constructor(private http: HttpClient) { }

  login(loginPayload): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.API + 'token/generate-token', loginPayload);
  }

}
