import { Component, OnInit } from '@angular/core';
import { Quote } from 'src/app/model/quote';
import { QuoteService } from 'src/app/_services/quote.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-quote-list',
  templateUrl: './quote-list.component.html',
  styleUrls: ['./quote-list.component.css']
})
export class QuoteListComponent implements OnInit {

  user : any ;
  quotes?: Quote[];
  currentQuote: Quote = {
    id:0,
    authorname: "",
    content: ""
  };
  currentIndex = -1;
  title = '';

  constructor(
    private  quoteService: QuoteService,
    private tokenStorageService:TokenStorageService
    ) { }

  ngOnInit(): void {
    this.retrieveQuotes();
  }

  initUser(){
    this.user = this.tokenStorageService.getUser();
  }

  retrieveQuotes(): void {
    this.initUser()
    this.quoteService.getAllQuoteByUser(this.user.id).subscribe(
        data => {
          this.quotes = data;
          console.log(data);
        },
        error => {
          console.log(error);
        }
        );
  }

  refreshList(): void {
    this.retrieveQuotes();
    this.currentQuote.authorname = "",
    this.currentQuote.content = ""
    this.currentIndex = -1;
    }


    setActiveQuote(quote: Quote, index: number): void {
      this.currentQuote = quote;
      this.currentIndex = index;
    }

  deleteQuote(){
    this.quoteService.delete(this.user.id, this.currentQuote.id)
    .subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      });
  }

}


