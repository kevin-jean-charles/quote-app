import { Component, OnInit } from '@angular/core';
import { Quote } from 'src/app/models/quote';
import { QuoteService } from 'src/app/services/quote.service';

@Component({
  selector: 'app-quote-list',
  templateUrl: './quote-list.component.html',
  styleUrls: ['./quote-list.component.css']
})
export class QuoteListComponent implements OnInit {
  quoteList: Quote[] = [];
  currentQuote = new Quote();
  currentIndex : number |undefined = -1

  constructor(private quoteService: QuoteService) { }

  ngOnInit(): void {
    this.onInit();
  }

  onInit() {
    const username = localStorage.getItem("username");
    this.quoteService.getByUsername(username).subscribe(
      (resp: any) => {
        console.log(resp);
        this.quoteList = resp;
      }      
    )
  }

  OnClick(){
    this.quoteService.deleteQuote(this.currentIndex).subscribe(
      (resp: any) => {
        console.log(resp);
        this.quoteList = resp;
      } ,   
      error => {
        console.log(error);
        
      })
  }

  setCurrentQuote(currentIndex:number, quote: Quote) {
    this.currentIndex = quote.id
    this.currentQuote.author 
    this.currentQuote.content 
    console.log(this.currentIndex);
  }
}
