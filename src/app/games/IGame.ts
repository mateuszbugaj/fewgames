import { AppService } from '../app.service';

export interface IGame {
    name: String;
    sketch;
    sketchId: String;
    appService: AppService;
}