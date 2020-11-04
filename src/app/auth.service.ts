import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IUser } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated: boolean = false;
  authenticatedUserName: String;

  constructor(private http: HttpClient) { }

  login(credentials: IUser){
    const headers: HttpHeaders = new HttpHeaders({
      "Content-Type": "application/json"
    });

    console.log("AuthService>logni>credentials: ");
    console.log(credentials);
    this.http.post("api/auth/login", credentials, {headers: headers}).subscribe(response => {
      console.log("AuthService>logni>response: ");
      console.log(response);
    });
  }
}
