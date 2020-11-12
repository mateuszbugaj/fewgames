import { Component, Input, OnInit } from '@angular/core';
import { gameEntry } from '../models/gameEntry';

@Component({
  selector: 'game-entry',
  templateUrl: './game-entry.component.html',
  styleUrls: ['./game-entry.component.css']
})
export class GameEntryComponent implements OnInit {

  @Input()
  entry: gameEntry;

  constructor() { }

  ngOnInit(): void {
  }

  formatDate(date: Date): String{
    var d: Date = new Date(date);
    return ("0" + d.getDate()).slice(-2) + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" +
    d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2) + ":" + ("0" + d.getSeconds()).slice(-2);
  }

}
