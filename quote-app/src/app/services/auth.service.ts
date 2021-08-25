import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogged = false;

  constructor( 
    private httpClient: HttpClient
    ) { }

    register(user: User){
      return this.httpClient.post(environment.apiBase + "/auth/register", user)
    }

    login(user: User){
      return this.httpClient.post(environment.apiBase + "/auth/login", user)
    }

    isLoggedIn(): boolean {
      return this.isLogged;
    }
    setLogged(value: boolean) {
      this.isLogged = value;
    }

    refreshToken(token: string) {
      return this.httpClient.post(environment.apiBase + '/auth/refreshtoken', {
        refreshToken: token
      }, httpOptions);
    }

}
