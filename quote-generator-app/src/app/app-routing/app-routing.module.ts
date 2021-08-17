import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { ProfileComponent } from '../profile/profile.component';
import { BoardUserComponent } from '../board-user/board-user.component';
import { AddQuoteComponent } from '../quote-feature/add-quote/add-quote.component';
import { QuoteListComponent } from '../quote-feature/quote-list/quote-list.component';
import { QuoteDetailsComponent } from '../quote-feature/quote-details/quote-details.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'add', component: AddQuoteComponent },
  { path: 'quotes/:id', component: QuoteDetailsComponent },
  { path: 'quotes', component: QuoteListComponent },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
