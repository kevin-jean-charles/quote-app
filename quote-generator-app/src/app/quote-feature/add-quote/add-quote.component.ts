import { Component, OnInit } from '@angular/core';
import { Quote } from 'src/app/model/quote';
import { QuoteService } from 'src/app/_services/quote.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-add-quote',
  templateUrl: './add-quote.component.html',
  styleUrls: ['./add-quote.component.css']
})
export class AddQuoteComponent implements OnInit {

  user : any ;
  quote: Quote = {
    id: -1,
    authorname: "",
    text: "",
  };
  submitted = false;

  constructor(
    private quoteService: QuoteService,
    private tokenStorageService:TokenStorageService
    ) { }

    ngOnInit(): void {
    }

  initUser(){
    this.user = this.tokenStorageService.getUser();
  }

  saveQuote(): void {
    this.initUser();
    const data = {
      
      text: this.quote.text,
      authorname: this.quote.authorname
    };

    this.quoteService.create(data, this.user.id)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log("errro: " + error);
        }
      );
  }

  newQuote(): void {
    this.submitted = false;
    this.quote = {
      id: -1,
      text: '',
      authorname: '',
    };
  }


}
