import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  [x: string]: any;


  jwTtoken = localStorage.getItem("token");

  constructor(public authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (this.jwTtoken != null) {
      request = this.addToken(request, this.jwTtoken);
    }
    return next.handle(request).pipe(  )}
  
  addToken(request: HttpRequest<any>, token: string) {
   return request.clone({
     setHeaders: {
       'Authorization': `Bearer ${token}`
     }
   });
 } 
}