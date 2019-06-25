import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  public API = '//localhost:8080';
  public CATEGORY_API = this.API + '/categories';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.CATEGORY_API);
  }

  get(id: string) {
    return this.http.get(this.CATEGORY_API + '/' + id);
  }
}
