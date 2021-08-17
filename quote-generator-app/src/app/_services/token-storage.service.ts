import { Injectable } from '@angular/core';

const TOKEN_APPLI = 'TOKEN_APPLI';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    localStorage.clear();
  }

  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_APPLI);
    localStorage.setItem(TOKEN_APPLI, token);
  }

  public getToken(): string | null {
    const token = localStorage.getItem(TOKEN_APPLI);
    console.log("token : " + token);
    
    return localStorage.getItem(TOKEN_APPLI);
  }

  public saveUser(user: any): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  
  public getUser(): any {
    const user = localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }
}
