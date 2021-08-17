import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Quote } from 'src/app/model/quote';
import { QuoteService } from 'src/app/_services/quote.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-quote-details',
  templateUrl: './quote-details.component.html',
  styleUrls: ['./quote-details.component.css']
})
export class QuoteDetailsComponent implements OnInit {
  currentQuote: Quote | undefined;
  message = '';

  constructor(
    private quoteService: QuoteService,
    private route: ActivatedRoute,
    private tokenStorageService:TokenStorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getQuote(this.route.snapshot.params.id);
  }

  getQuote(id: string): void {
    this.quoteService.getQuoteById(id)
      .subscribe(
        (data: any) => {
          this.currentQuote = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updateQuote(): void {
    this.message = '';

    this.quoteService.updateQuote(this.currentQuote?.id, this.currentQuote)
      .subscribe(
        response => {
          console.log(response);
          // this.message = response.message ? response.message : 'Cette citation a été mis à jour avec succès';
        },
        error => {
          console.log(error);
        });
  }
}


