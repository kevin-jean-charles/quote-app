import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Quote } from '../model/quote';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {

  constructor(private httpClient : HttpClient) { }

  getQuoteAtRandom(): Observable<Quote> {
    return this.httpClient.get<Quote>(`${environment.QUOTE_API_URL}/random`);
  }

  create(data: any, id: any): Observable<any> {
    return this.httpClient.post(`${environment.QUOTE_API_URL}/users/${id}`, data);
  }

  getAllQuoteByUser(id: any): Observable<any> {
    return this.httpClient.get(`${environment.QUOTE_API_URL}/users/${id}`)
  }

  delete(userId: any, quoteId: any): Observable<any> {
    return this.httpClient.delete(`${environment.QUOTE_API_URL}/users/${userId}/delete/${quoteId}`)
  }

  getQuoteById(userId: any){
    return this.httpClient.get(`${environment.QUOTE_API_URL}/${userId}`)
  }

  updateQuote(userId:any, data:any) {
    return this.httpClient.put(`${environment.QUOTE_API_URL}/users/${userId}`, data)
  }
}

