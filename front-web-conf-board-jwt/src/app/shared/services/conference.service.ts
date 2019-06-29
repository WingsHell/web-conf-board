import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api.response';

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {

  public CONFERENCE_API = 'http://localhost:8080/conferences';

  constructor(private http: HttpClient) { }

  getAll(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.CONFERENCE_API);
  }

  get(id: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.CONFERENCE_API + '/' + id);
  }

  getByTitle(title: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.CONFERENCE_API + '/title/' + title);
  }

  getByVoted(voted: boolean): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.CONFERENCE_API + '/voted/' + voted);
  }

  getTop10ByRate(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.CONFERENCE_API + '/top10');
  }

  save(conference: any): Observable<ApiResponse> {
      let result: Observable<ApiResponse>;
      console.log('put log: ' + + JSON.stringify(conference));
      result = this.http.post<ApiResponse>(this.CONFERENCE_API, conference);
      return result;
    }


  update(conference: any): Observable<ApiResponse> {
    let result: Observable<ApiResponse>;
    if (conference['href']) {
      console.log('post log: ' + JSON.stringify(conference));
      result = this.http.put<ApiResponse>(conference.href, conference);
    }
    return result;
  }

  /*save(conference: any): Observable<ApiResponse> {
    let result: Observable<ApiResponse>;
    if (conference['href']) {
      result = this.http.put<ApiResponse>(conference.href, conference);
    } else {
      console.log('post log: ' + JSON.stringify(conference));
      result = this.http.post<ApiResponse>(this.CONFERENCE_API , conference);
    }
    return result;
  }*/

  remove(href: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(href);
  }
}
