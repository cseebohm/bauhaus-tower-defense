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

public class TowerA extends Tower {

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
        placeTower();

        if(!isMoving){
            this.target = state.findNearestEnemy(currentLoc);

            if(!(this.target == null) && state.getDistance(currentLoc, target.getLoc()) < 75 && !(this.target == currentTarget))
            {            
                this.currentTarget = this.target;
                AttackA fire = new AttackA(40, control, state, target, currentLoc);
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
}
