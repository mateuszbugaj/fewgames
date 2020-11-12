import * as p5 from 'p5';
import { element } from 'protractor';
import { start } from 'repl';
import { AppService } from '../app.service';
import { IGame } from './IGame';

export class AsteroidsGame implements IGame {
    name: String = "Asteroids";
    sketchId: String;
    appService: AppService;

    constructor(app: AppService) {
        this.appService = app;
    }

    sketch = (p: p5) => {
        let started = false;
        let inFocus = false;
        let ship: Ship;

        let aPressed: boolean = false;
        let dPressed: boolean = false;
        let wPressed: boolean = false;

        let obstacles: Array<Obstacle> = [];
        let score: number = 0;
        
        p.setup = () => {
            GraphicObject.p = p;
            p.textAlign(p.CENTER);

            let height = 600;
            let width = height;
            let canvas = p.createCanvas(width, height);
            canvas.parent("sketch-holder:" + this.sketchId);

            ship = new Ship(new p5.Vector().set(width/2, height/2 + 100));

            for(let i = 0;i < 10; i++){
                obstacles.push(new Obstacle(new p5.Vector().set(p.random(width), p.random(-p.height*2, -200)), p.random(50,200)));
            }
            obstacles.push(new Obstacle(new p5.Vector().set(100, 100), 0.1));
        }

        p.draw = () => {
            p.background(78, 88, 105);

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

            if(started && !ship.dead){
                obstacles.forEach(i => {i.show(); i.update()});
                if(p.frameCount % 40 == 0){
                    score++;
                }
                ship.update();
            }

            ship.show();
            
            p.fill(78, 88, 105);
            p.strokeWeight(3);
            p.stroke(236, 190, 105);

            p.textSize(30);
            p.text("Ponits\n" + score, p.width/2, 40);
            p.textAlign(p.CENTER);

            if (started == false) {
                p.textSize(30);
                p.text("CLICK W, S, A, or D TO START", p.width/2, p.height/2);
            }

            if(aPressed){
                ship.dir-=0.1;
            }

            if(dPressed){
                ship.dir+=0.1;
            }

            if(wPressed){
                if(ship.speed < 5){
                    ship.speed+=0.4;
                }
            }

            if (obstacles.some(i => compareTwoPositions(i.pos, ship.pos, i.size/2) ||
                ship.pos.x < 0 || ship.pos.x > p.width || ship.pos.y < 0 || ship.pos.y > p.height)) {
                ship.dead = true;
            }
            
            if(ship.dead){
                p.clear();
                p.textSize(60);
                p.text("Game over!", p.width / 2, p.height / 2);
                p.textSize(30);
                p.text("Score: " +score+ "\nYou can play again by pressing 'Q'\nPress 'E' to save your score\non the leaderboard!", p.width / 2, p.height / 2 + 30);    
            }
        }

        p.keyPressed = () => {
            if(inFocus){
                switch(p.key){
                    case 'd': dPressed = true; break; 
                    case 'a': aPressed = true; break; 
                    case 'w': wPressed = true; break; 
                }

                if(ship.dead){
                    if(p.key == 'q'){
                        startOver();
                    }

                    if(p.key == 'e'){
                        this.appService.saveGameEntry(this.name, score);
                        startOver();
                    }
                }
    
                if (started == false && ['w', 's', 'a', 'd'].includes(p.key)) {
                    started = true;
                }
            }
        }

        p.keyReleased = () => {
            if(inFocus){
                switch(p.key){
                    case 'd': dPressed = false; break; 
                    case 'a': aPressed = false; break; 
                    case 'w': wPressed = false; break;  
                }
            }
        }

        function startOver() {
            ship.dead = false;
            ship.pos.set(p.width/2, p.height/2 + 100)
            obstacles.forEach(i => {i.speed = 1; i.pos.set(p.random(p.width), p.random(-p.height*2, -200)), p.random(50,200);});
            score = 0;
        }
    }
}

function compareTwoPositions(vector1: p5.Vector, vector2: p5.Vector, margin: number): boolean {
    return (Math.abs(vector1.x - vector2.x) <= margin && Math.abs(vector1.y - vector2.y) <= margin);
}

class GraphicObject{
    static p: p5;

    constructor(public pos: p5.Vector){}
}

class Ship extends GraphicObject{
    public speed: number = 0;
    public dir: number = 0.0;
    public dead: boolean;

    constructor(pos: p5.Vector){
        super(pos);
    }

    show(){
        GraphicObject.p.push();
        GraphicObject.p.translate(this.pos);
        GraphicObject.p.rotate(this.dir);
        GraphicObject.p.noStroke();
        GraphicObject.p.fill(135, 206, 235);
        GraphicObject.p.rect(-10, -25 ,20, 50);

        GraphicObject.p.pop();
    }

    update(){
        this.pos.add(this.speed * GraphicObject.p.sin(this.dir), -this.speed * GraphicObject.p.cos(this.dir));

        if(this.speed - 0.1 >= 0){
            this.speed -= 0.1;
        }

    }
}

class Obstacle extends GraphicObject{
    public speed: number = 1;
    public colorVar: number;

    constructor(pos: p5.Vector, public size: number){
        super(pos);
        this.colorVar = GraphicObject.p.random(-10, 10);
    }

    show(){
        GraphicObject.p.push();
        GraphicObject.p.translate(this.pos);
        GraphicObject.p.noStroke();
        GraphicObject.p.fill(236 + this.colorVar, 190, 105);
        GraphicObject.p.ellipse(0, 0, this.size, this.size);

        GraphicObject.p.pop();
    }

    update(){
        this.pos.add(0, this.speed);
        this.speed += 0.0002;

        if(this.pos.y > GraphicObject.p.height + this.size){
            this.pos.set(GraphicObject.p.random(GraphicObject.p.width), GraphicObject.p.random(-GraphicObject.p.height*2, -200));
        }
    }
}