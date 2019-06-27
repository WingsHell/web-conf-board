import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {

  public API = '//localhost:8080';
  public CONFERENCE_API = this.API + '/conferences';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.CONFERENCE_API);
  }

  get(id: string) {
    return this.http.get(this.CONFERENCE_API + '/' + id);
  }

  getByTitle(title: string): Observable<any> {
    return this.http.get(this.CONFERENCE_API + '/title/' + title);
  }

  getByVoted(voted: boolean): Observable<any> {
    return this.http.get(this.CONFERENCE_API + '/voted/' + voted);
  }

  getTop10ByRate(): Observable<any> {
    return this.http.get(this.CONFERENCE_API + '/top10');
  }

  save(conference: any): Observable<any> {
    let result: Observable<Object>;
    if (conference['href']) {
      result = this.http.put(conference.href, conference);
    } else {
      console.log('post log: ' + JSON.stringify(conference));
      result = this.http.post(this.CONFERENCE_API , conference);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
