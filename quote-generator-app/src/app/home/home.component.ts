import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Quote } from '../model/quote';
import { QuoteService } from '../_services/quote.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  randQuote!: Quote;

  constructor(
    private quoteService: QuoteService,
    private userService: UserService
    ) { }

    ngOnInit(): void {
      this.userService.getPublicContent().subscribe(
        data => {
          this.content = data;
        },
        err => {
          this.content = JSON.parse(err.error).message;
        }
      );
    }

  getQuoteAtRand():void {
    this.quoteService.getQuoteAtRandom().subscribe(
      (resp: Quote) => {
        this.randQuote = resp;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      })
    }

}
