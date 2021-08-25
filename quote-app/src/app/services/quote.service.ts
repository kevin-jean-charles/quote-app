import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Quote } from '../models/quote';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {

  constructor(
    private httpClient: HttpClient
   ) { }

   
  getAtRandom(): Observable<Quote> {
    return this.httpClient.get<Quote>(environment.apiBase + '/quotes/random');
  }

  getByUsername(username: string | null): Observable<Quote[]>{
    return this.httpClient.get<Quote[]>(environment.apiBase + '/quotes/users/'+ username);
  }

  addQquote(username: string | null, quote: Quote): Observable<Quote>{
    return this.httpClient.post<Quote>(environment.apiBase + '/quotes/users/'+ username, quote);
  }

  deleteQuote(quoteId: number | undefined){
    return this.httpClient.delete<Quote>(environment.apiBase + '/quotes/'+ quoteId);
  }

  getQuoteById(quoteId: number){
    return this.httpClient.get<Quote>(environment.apiBase + '/quotes/'+ quoteId);
  }
}
