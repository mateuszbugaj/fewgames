import * as p5 from 'p5';
import { AppService } from '../app.service';
import { IGame } from './IGame';

export class SnakeGame implements IGame {
    name: String = "Snake";
    sketchId: String;
    appService: AppService;

    constructor(app: AppService) {
        this.appService = app;
    }

    sketch = (p: p5) => {
        TileObject.p = p;
        let started = false;
        let inFocus = false;
        
        let snake: Snake;
        let food: Food;
        let tileSize: number;
        let maxRow: number;
        let maxCol: number;
        
        let canvas: p5.Renderer;
        
        p.setup = () => {
            p.textAlign(p.CENTER);
            
            let width = 700;
            let height = width * 0.5;
            canvas = p.createCanvas(width, height);
            canvas.parent("sketch-holder:" + this.sketchId);
            
            tileSize = width / 30;
            TileObject.tileSize = tileSize;
            maxRow = p.int(p.height / tileSize);
            maxCol = p.int(p.width / tileSize);
            
            snake = new Snake(p.int(maxCol / 2) * tileSize, p.int(maxRow / 2 + 3) * tileSize);
            food = new Food();
            food.randomize(maxCol, maxRow);
        }

        p.draw = () => {
            p.clear();

            // check if mouse left button clicked no the canvas making it in focus
            if(p.mouseIsPressed && p.mouseButton == p.LEFT ){
                if(p.mouseX > 0 && 
                    p.mouseX < p.width && 
                    p.mouseY > 0 && 
                    p.mouseY < p.height){

                    inFocus = true;
                } else {
                    inFocus = false;
                }
            }

            food.show();
            snake.show();

            // game border
            p.noFill();
            p.rect(0, 0, p.width, p.height);

            if (started && p.frameCount % snake.speed == 0) {
                snake.update();
            }

            if (compareTwoPositions(snake.pos, food.pos, tileSize / 2)) {
                food.randomize(maxCol, maxRow);
                snake.add();
            }

            if (snake.pos.x < 0 || 
                snake.pos.x >= maxCol * tileSize || 
                snake.pos.y < -tileSize/2 || 
                snake.pos.y >= maxRow * tileSize - 1) {
                snake.dead = true;
            }

            p.textAlign(p.LEFT);
            p.textSize(30);
            p.text(snake.length, 10, 30);
            p.textAlign(p.CENTER);

            if (snake.dead) {
                p.clear();
                p.textSize(60);
                p.text("Game over!", p.width / 2, p.height / 2);
                p.textSize(30);
                p.text("Score: " + snake.length + "\nYou can play again by pressing 'Q'\nPress 'E' to save your score on the leaderboard!", p.width / 2, p.height / 2 + 30);
            }

            if (started == false) {
                p.textSize(30);
                p.text("CLICK W, S, A, or D TO START", p.width / 2, p.height / 2);
            }
        }

        p.keyPressed = () => {
            if(inFocus){
                switch(p.key){
                    case 'd': snake.setDirection(1, 0); break; 
                    case 'a': snake.setDirection(-1, 0); break; 
                    case 'w': snake.setDirection(0, -1); break; 
                    case 's': snake.setDirection(0, 1); break; 
                }
    
                if (snake.dead) {
                    if (p.key == 'q') {
                        startOver();
                    }
    
                    if (p.key == 'e') {
                        this.appService.saveGameEntry(this.name, snake.length);
                        startOver();
                    }
                }
    
                if (started == false && ['w', 's', 'a', 'd'].includes(p.key)) {
                        started = true;
                }
            }
        }

        function startOver() {
            snake = new Snake(p.int(maxCol / 2) * tileSize, p.int(maxRow / 2) * tileSize);
            food.randomize(maxCol, maxRow);
        }
    }
}

function compareTwoPositions(vector1: p5.Vector, vector2: p5.Vector, margin: number): boolean {
    return (Math.abs(vector1.x - vector2.x) <= margin && Math.abs(vector1.y - vector2.y) <= margin);
}

class TileObject{
    static p: p5;
    static tileSize: number;
    public pos: p5.Vector = new p5.Vector();
}

class Snake extends TileObject{
    public speed: number = 7; // more means slower
    public direction: p5.Vector = new p5.Vector().set(1, 0);
    public positions: Array<p5.Vector> = [];
    public length: number = 1;
    public dead = false;

    constructor(posX: number, posY: number) {
        super();
        this.pos.set(posX, posY);
    }

    show() {
        TileObject.p.fill(236, 190, 105);
        TileObject.p.strokeWeight(3);
        TileObject.p.stroke(78, 88, 105);
        for (let segment of this.positions) {
            TileObject.p.rect(segment.x, segment.y, TileObject.tileSize, TileObject.tileSize);
        }
        TileObject.p.fill(78, 88, 105);
        TileObject.p.rect(this.pos.x, this.pos.y, TileObject.tileSize, TileObject.tileSize);
    }

    update() {
        this.pos.add(this.direction.x * TileObject.tileSize, this.direction.y * TileObject.tileSize);
        this.positions.unshift(this.pos.copy());
        if (this.positions.length > this.length) {
            this.positions.pop();
        }
        if (this.positions.filter(pos => compareTwoPositions(pos, this.pos, TileObject.tileSize / 2)).length > 1) {
            this.dead = true;
        }
    }

    add() {
        this.length++;
    }

    setDirection(x: number, y: number) {
        this.direction.set(x, y);
    }
}

class Food extends TileObject{
    constructor() {
        super();
    }

    randomize(cols: number, rows: number) {
        this.pos.set(
            TileObject.p.int(TileObject.p.random(0, cols)) * TileObject.tileSize, 
            TileObject.p.int(TileObject.p.random(0, rows)) * TileObject.tileSize
            );
    }

    show() {
        TileObject.p.fill(135, 206, 235);
        TileObject.p.noStroke();
        TileObject.p.rect(this.pos.x, this.pos.y, TileObject.tileSize, TileObject.tileSize);
    }
}