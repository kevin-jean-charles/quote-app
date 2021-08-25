import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RandomComponent } from './quote/random/random.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { QuoteListComponent } from './quote/quote-list/quote-list.component';
import { AuthService } from './services/auth.service';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { AuthGuard } from './guard/auth.guard';
import { AddQuoteComponent } from './quote/add-quote/add-quote.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RandomComponent,
    LoginComponent,
    SignupComponent,
    QuoteListComponent,
    AddQuoteComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  providers: [
    AuthService,
    AuthGuard,
    {    
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
