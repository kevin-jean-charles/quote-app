import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddQuoteComponent } from './quote/add-quote/add-quote.component';
import { QuoteListComponent } from './quote/quote-list/quote-list.component';
import { RandomComponent } from './quote/random/random.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  { path: 'rand', component: RandomComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: '', pathMatch: 'full', redirectTo: '/rand' },
  { path: 'quotes', component: QuoteListComponent },
  { path: 'add', component: AddQuoteComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
