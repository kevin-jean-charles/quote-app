
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Quote } from 'src/app/models/quote';
import { QuoteService } from 'src/app/services/quote.service';

@Component({
  selector: 'app-add-quote',
  templateUrl: './add-quote.component.html',
  styleUrls: ['./add-quote.component.css']
})
export class AddQuoteComponent implements OnInit {
  quote = new Quote();
  submitted = false;
  
  constructor(private quoteService: QuoteService) { }

  ngOnInit(): void {
  }

  saveqQuote(): void {
    const data = {
      content: this.quote.content,
      author: this.quote.author
    };
    
    const username = localStorage.getItem("username");
    this.quoteService.addQquote(username, data)
      .subscribe(
        response => {
          this.submitted = true;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  // newquote(): void {
  //   this.quote = {
  //     content:'',
  //     author: ''
  //   };
  // }

}
