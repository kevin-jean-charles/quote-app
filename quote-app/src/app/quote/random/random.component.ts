import { Component, OnInit } from '@angular/core';
import { Quote } from 'src/app/models/quote';
import { QuoteService } from 'src/app/services/quote.service';

@Component({
  selector: 'app-random',
  templateUrl: './random.component.html',
  styleUrls: ['./random.component.css']
})
export class RandomComponent implements OnInit {
  randomQuote = new Quote();
  constructor(private quoteService: QuoteService) { }

  ngOnInit(): void {
    this.onClick(); 
  }

  onClick() {
    this.quoteService.getAtRandom().subscribe(
      (data: any) => {
        console.log(data.author);
        this.randomQuote.id = data.id;
        this.randomQuote.author = data.author;
        this.randomQuote.content = data.content;
      }
    )
  }
}
