import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated: boolean = false;
  authenticatedUserName: String;

  constructor(private http: HttpClient) { }

  login(credentials) {

    const headers = new HttpHeaders(credentials ? {
        authorization : 'Basic ' + btoa(credentials.name + ':' + credentials.password)} : {});

    this.http.get('api/v1/user', {headers: headers}).subscribe(response => {
        if (response['name']) {
            this.authenticated = true;
            console.log("Got name: ");
            console.log(response['name']);
            this.authenticatedUserName = response['name'];
        } else {
            this.authenticated = false;
        }
    });

}
}
