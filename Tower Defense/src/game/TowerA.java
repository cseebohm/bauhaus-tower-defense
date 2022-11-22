/**
 * TowerA class defines tower type A, the lowest level tower
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TowerA extends GameObject implements Clickable{

    Control control;
    State state;
    
    boolean isMoving;

    int x;
    int y;

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
	}

    /**
     * this method draws towerA at x and y
     * 
     * @param Graphics g
     */
	public void draw(Graphics g) {
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
    }

}
