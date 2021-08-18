import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  // getPublicContent(): Observable<any> {
  //   return this.http.get(environment.TEST_API_URL + '/all', { responseType: 'text' });
  // }

  // getUserBoard(): Observable<any> {
  //   return this.http.get(environment.TEST_API_URL  + '/user', { responseType: 'text' });
  // }
}
