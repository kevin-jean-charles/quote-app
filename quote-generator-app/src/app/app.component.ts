import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title='quote-generator-app';
  isLoggedIn = false;
  username?: string;
  token?: string;

  constructor(private tokenStorageService: TokenStorageService) { }


  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      // this.token = 
    }
  }

  
  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
