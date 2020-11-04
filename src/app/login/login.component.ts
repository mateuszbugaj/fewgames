import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { IUser } from '../models/user';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credentials: IUser = { name: "", password: "" };

  constructor(private authService: AuthService) { }

  login(){
    this.authService.login(this.credentials);
  }

  logout(){

  }

  authenticated() {
    return false;
  }

}
