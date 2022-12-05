package game;

import java.awt.Graphics;
import java.awt.Point;
import path.Path;

public class AttackA extends GameObject{

    int x, y;

    Control control;
    State state;

    Path effectPath = new Path();

    double percentage;
    double velocity;

    Enemy target;


    public AttackA(double velocity, Control control, State state, Enemy target, Point towerLoc){
        isExpired = false;
        isVisible = true;

        this.velocity = velocity;
        this.percentage = 0;

        this.control = control;
        this.state = state;

        this.effectPath.add(towerLoc.x + 20, towerLoc.y - 20);
        this.effectPath.add(target.getLoc().x, target.getLoc().y);

        this.target = target;
    }

    public void update(double timeElapsed) {
        this.effectPath.remove(1);
        this.effectPath.add(target.getLoc().x, target.getLoc().y);

        double movePerFrame = this.velocity * state.getElapsedTime() * Math.pow(10, -12.0);
        percentage += movePerFrame / control.getPath().getLength();

        Point currentLoc = effectPath.convertToCoordinates(percentage);
        this.x = currentLoc.x;
        this.y = currentLoc.y;
            
        if(percentage >= (1)){
            //expire current effect
            isExpired = true;

            //expire enemy
            target.setExpired();
        }
    }


    public void draw(Graphics g) {
        g.drawImage(control.getImage("enemyB.png"), this.x, this.y, null);	
    }
    
}
