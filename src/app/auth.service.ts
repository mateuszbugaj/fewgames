import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IUser } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated: boolean = false;
  authenticatedUserName: String;

  constructor(private http: HttpClient) { }

  register(userDto: IUser){
      console.log("Registration");
      console.log(userDto);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.http.post("api/v1/saveUser", userDto, httpOptions).subscribe(() => {
        this.login(userDto);
      });
  }

  login(credentials) {
    console.log("Login")
    console.log(credentials);

    const headers = new HttpHeaders(credentials ? {
        authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)} : {});

    this.http.get('api/v1/user', {headers: headers}).subscribe(response => {
        if (response['name']) {
            this.authenticated = true;
            this.authenticatedUserName = response['name'];
        } else {
            this.authenticated = false;
        }
    });

}
}
