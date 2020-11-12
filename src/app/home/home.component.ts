import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { AuthService } from '../auth.service';
import { AsteroidsGame } from '../games/asteroids';
import { IGame } from '../games/IGame';
import { SnakeGame } from '../games/snake';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  snakeGame: IGame;
  asteroids: IGame;

  constructor(private app: AppService, private auth: AuthService) { 
    this.snakeGame = new SnakeGame(this.app);
    this.asteroids = new AsteroidsGame(this.app);
   }

   authenticated() {
    return this.auth.authenticated;
  }
}
