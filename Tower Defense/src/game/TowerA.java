/**
 * TowerA class defines tower type A, the lowest level tower
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class TowerA extends GameObject implements Clickable{

    Control control;
    State state;
    
    boolean isMoving;

    int x;
    int y;

    Enemy target;
    Enemy currentTarget;

    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param towerA
     * @param control
     * @param state
     */
    public TowerA(Control control, State state)
    {        
        isVisible = true;
        isExpired = false;
        isMoving = true;

        this.target = null;
        this.currentTarget = null;

        this.control = control;
        this.state = state;

        this.x = control.getMouseX()-20;
        this.y = control.getMouseY()-20;
    }
    
    /**
     * this method updates after a certain amound of time has elapsed, if the tower is moving then the towers location is updated 
     * as the current x and y and consumeClick is called
     * 
     * @param timeElapsed
     */
	public void update(double timeElapsed) {
        if(isMoving){

            this.x = control.getMouseX()-20;
            this.y = control.getMouseY()-20;
            consumeClick();
        }	

        else{
            Point currentLoc = new Point(this.x,this.y);
            this.target = state.findNearestEnemy(currentLoc);

            if(!(this.target == null) && state.getDistance(currentLoc, target.getLoc()) < 75 && !(this.target == currentTarget))
            {            
                this.currentTarget = this.target;
                AttackA fire = new AttackA(25, control, state, target, currentLoc);
                state.addGameObject(fire);
            }
        }
	}

    /**
     * this method draws towerA at x and y
     * 
     * @param Graphics g
     */
	public void draw(Graphics g) {
		

        Color transparentWhite = new Color(60, 255, 255, 90);
        g.setColor(transparentWhite);

        g.fillOval((int)this.x - 55, (int)this.y - 55, 150,150);
        g.drawImage(control.getImage("towerA.png"), this.x, this.y, null);	
	}

    /**
     * this method is executed when isMoving is true in the update method
     * all it does is change isMoving to false after a click
     * 
     */
    public void consumeClick() {
        if(control.getClick())
            isMoving=false;

        //out of bounds, remove game object
        if(this.x<0 || this.x>600 || this.y<0 || this.y>600)
            isMoving=true;
        
        else if(state.getPixelColor(this.x, this.y).equals(state.getPathColor()))
            isMoving = true;
    }

}
