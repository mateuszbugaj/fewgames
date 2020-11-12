import * as p5 from 'p5';
import { element } from 'protractor';
import { AppService } from '../app.service';
import { IGame } from './IGame';

export class TetrisGame implements IGame {
    name: String = "Tetris";
    sketchId: String;
    appService: AppService;

    constructor(app: AppService) {
        this.appService = app;
    }

    sketch = (p: p5) => {
        TileObject.p = p;
        let started = false;
        let inFocus = false;
        
        let tileSize: number;
        let maxRow: number;
        let maxCol: number;
        let elements: Array<Tetromino> = [];
        
        p.setup = () => {
            p.textAlign(p.CENTER);
            
            let height = 600;
            let width = height;
            let canvas = p.createCanvas(width, height);
            canvas.parent("sketch-holder:" + this.sketchId);
            
            tileSize = width / 30;
            TileObject.tileSize = tileSize;
            maxRow = p.int(p.height / tileSize);
            maxCol = p.int(p.width / tileSize);

            elements.push(new Square().setPos(0, tileSize*5), new Straight().setPos(0, 0));
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

            // game border
            p.noFill();
            p.stroke(78, 88, 105);
            p.rect(0, 0, p.width, p.height);

            // ui rect
            p.fill(78, 88, 105);
            p.rect(400, 0, p.width-400, p.height);

            elements.forEach(i => i.show());

            if (started && p.frameCount % 8 == 0) {
                elements.forEach(i => i.update(elements));
            }
            
            // p.fill(0);
            p.strokeWeight(3);
            p.stroke(78, 88, 105);

            p.stroke(236, 190, 105);
            p.textSize(30);
            p.text("Ponits\n205", 400 + (p.width-400)/2, 30);
            p.textAlign(p.CENTER);

            if (started == false) {
                p.stroke(78, 88, 105);
                p.textSize(30);
                p.text("CLICK W, S, A, or D TO START", 0, p.height/2, 400, p.height);
            }
        }

        p.keyPressed = () => {
            if(inFocus){
                switch(p.key){
                    case 'd':  break; 
                    case 'a':  break; 
                    case 'w':  break; 
                    case 's':  break; 
                }
    
                if (started == false && ['w', 's', 'a', 'd'].includes(p.key)) {
                    started = true;
                    p.loop();
                }
            }
        }

        function startOver() {

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

class Tetromino extends TileObject{
    public atRest: boolean = false;

    constructor(public shape: Array<p5.Vector>) { 
        super();
    }

    setPos(x: number, y: number): Tetromino{
        this.pos.set(x, y);
        return this;
    }

    show(){
        TileObject.p.push();
        TileObject.p.translate(this.pos);

        TileObject.p.stroke(78, 88, 105);
        TileObject.p.fill(236, 190, 105);
        this.shape.forEach(element => TileObject.p.rect(element.x, element.y, TileObject.tileSize, TileObject.tileSize));

        TileObject.p.pop();
    }

    update(elements: Array<Tetromino>){
        if(!this.atRest){

            // if(this.shape.every(i => i.y + this.pos.y < TileObject.p.height-TileObject.tileSize) && 
            // this.shape.every(i => elements.every(j => j.shape.every(k => i.y + this.pos.y + TileObject.tileSize < k.y + j.pos.y)))){
            //     this.pos.y += TileObject.tileSize;
            // } else {
            //     this.atRest = true;
            // }

            if(this.shape.every(i => i.y + this.pos.y < TileObject.p.height-TileObject.tileSize)){
                this.pos.y += TileObject.tileSize;
            } else {
                this.atRest = true;
            }
        }
    }
}

class Square extends Tetromino{
    constructor(){
        super([
            new p5.Vector().set(0, 0), 
            new p5.Vector().set(TileObject.tileSize, 0),
            new p5.Vector().set(0, TileObject.tileSize),
            new p5.Vector().set(TileObject.tileSize, TileObject.tileSize),
        ]);
    }
}

class Straight extends Tetromino{
    constructor(){
        super([
            new p5.Vector().set(0, 0), 
            new p5.Vector().set(0, TileObject.tileSize),
            new p5.Vector().set(0, TileObject.tileSize * 2),
            new p5.Vector().set(0, TileObject.tileSize* 3),
        ]);
    }
}

class L_tetromino extends Tetromino{
    constructor(){
        super([
            new p5.Vector().set(0, 0), 
            new p5.Vector().set(0, TileObject.tileSize),
            new p5.Vector().set(0, TileObject.tileSize * 2),
            new p5.Vector().set(0, TileObject.tileSize* 3),
            new p5.Vector().set(TileObject.tileSize, TileObject.tileSize* 3),
        ]);
    }
}

class T_tetromino extends Tetromino{
    constructor(){
        super([
            new p5.Vector().set(0, 0), 
            new p5.Vector().set(TileObject.tileSize, 0),
            new p5.Vector().set(TileObject.tileSize * 2, 0),
            new p5.Vector().set(TileObject.tileSize, TileObject.tileSize),
        ]);
    }
}

class skew extends Tetromino{
    constructor(){
        super([
            new p5.Vector().set(TileObject.tileSize, 0),
            new p5.Vector().set(TileObject.tileSize * 2, 0),
            new p5.Vector().set(0, TileObject.tileSize),
            new p5.Vector().set(TileObject.tileSize, TileObject.tileSize),
        ]);
    }
}