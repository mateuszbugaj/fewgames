import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { IUser } from '../models/user';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credentials: IUser = { username: "", password: "" };

  constructor(public authService: AuthService) { }

  login(){
    this.authService.register(this.credentials);
  }

  authenticated() {
    return this.authService.authenticated;
  }

}
