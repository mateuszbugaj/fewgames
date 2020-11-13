import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { gameEntry } from './models/gameEntry';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  saveGameEntry(gameName: String, score: Number){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    const entry = {
      userName: this.auth.authenticatedUserName,
      gameName: gameName,
      score: score
    }

    this.http.post("api/v1/saveEntry", entry, httpOptions).subscribe(() => {});
  }

  getGameEntries(gameName: String){
    return this.http.get<gameEntry[]>("api/v1/entries/"+gameName);
  }
}
