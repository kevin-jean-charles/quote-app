import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user = new User();
  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm){
    console.log(form.value);
    this.user.username = form.value.username;
    this.user.password = form.value.password
    this.authService.register(this.user).subscribe(
      (resp: any) => {
        console.log(resp);
        console.log("user has been register");
      }
    )
  }

}
