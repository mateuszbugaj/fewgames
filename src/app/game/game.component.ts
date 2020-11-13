import { Component, Input, OnInit } from '@angular/core';
// https://indepth.dev/creating-a-sketchpad-with-angular-and-p5js/
import * as p5 from 'p5'; //npm i p5, npm install @types/p5
// https://www.npmjs.com/package/uuid
import { v4 as uuidv4 } from 'uuid';
import { AppService } from '../app.service';
import { IGame } from '../games/IGame';
import { gameEntry } from '../models/gameEntry';

@Component({
  selector: 'game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  sketchHolderId:String = uuidv4();
  entries: gameEntry[];

  @Input()
  public gameCode: IGame;

  constructor(private app: AppService) {

  }

  ngOnInit(): void {
    this.gameCode.sketchId = this.sketchHolderId;
    new p5(this.gameCode.sketch);
    this.getEntries();
  }

  getEntries(){
    this.app.getGameEntries(this.gameCode.name).subscribe(response => {
      console.log("Game entries: ");
      console.log(response);

      this.entries = response.sort((e1, e2) => {
        if(e1.score < e2.score){
          return 1;
        }

        if(e1.score > e2.score){
          return -1;
        }

        return 0;
      });
    });
  }

}
