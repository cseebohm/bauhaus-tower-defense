/**
 * this class defines attackB
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import path.Path;

public class AttackB extends Attack{

    public AttackB(double velocity, Control control, State state, Enemy target, Point towerLoc){
        isExpired = false;
        isVisible = true;

        this.velocity = velocity;
        this.percentage = 0;

        this.control = control;
        this.state = state;

        this.x = towerLoc.x+13;
        this.y = towerLoc.y-13;

        this.effectPath = new Path();

        this.effectPath.add(x, y);
        this.effectPath.add(target.getLoc().x, target.getLoc().y);

        this.target = target;
    }

    public void update(double timeElapsed) {
        //update path as object moves
        this.effectPath.remove(1);
        this.effectPath.add(target.getLoc().x, target.getLoc().y);

        //calculate move per frame and update percentage
        double movePerFrame = this.velocity * state.getElapsedTime() * Math.pow(10, -12.0);
        percentage += movePerFrame / control.getPath().getLength();

        //convert to coordinates
        Point currentLoc = effectPath.convertToCoordinates(percentage);
        this.x = currentLoc.x;
        this.y = currentLoc.y;

        //when enemy is hit, expire attack and give damage    
        if(percentage >= (1)){
            //expire current effect
            isExpired = true;

            //expire enemy
            target.takeDamage(2);
        }
    }


    public void draw(Graphics g) {


        g.drawImage(control.getImage("fireB.png"), this.x, this.y, null);	
    }
    
}
