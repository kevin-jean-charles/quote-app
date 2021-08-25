import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  constructor(
    private authService: AuthService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm){
    console.log(form.value);
    this.user.username = form.value.username;
    this.user.password = form.value.password
    this.authService.login(this.user).subscribe(
      (resp: any) => {
        console.log(resp);
        console.log(resp.username);
        localStorage.test = "test"
        console.log(localStorage.test);
        
        localStorage.setItem("token", resp.jwtToken)
        localStorage.setItem("username", resp.username)  
        localStorage.setItem("username", resp.username)  
        


        console.log("user has been login");
        this.authService.setLogged(true)
      }
    )
  }
}
