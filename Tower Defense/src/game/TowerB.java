/**
 * TowerB class defines tower type A, the lowest level tower
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class TowerB extends Tower{

    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param towerA
     * @param control
     * @param state
     */
    public TowerB(Control control, State state)
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

        this.timeFired = state.getTotalTime();
    }
    
    /**
     * this method updates after a certain amound of time has elapsed, if the tower is moving then the towers location is updated 
     * as the current x and y and consumeClick is called
     * 
     * @param timeElapsed
     */
	public void update(double timeElapsed) {
        placeTower();	

        //when tower is active
        if(!isMoving){
            this.target = state.findNearestEnemy(currentLoc);

            if(!(this.target == null) && state.getDistance(currentLoc, target.getLoc()) < 150 && !(this.target == currentTarget) && (state.getTotalTime() - timeFired) > (.75 * Math.pow(10,3)))
            {            
                this.currentTarget = this.target;
                AttackB fire = new AttackB(60, control, state, target, currentLoc);
                state.addGameObject(fire);
                timeFired = state.getTotalTime();
            }
        }
	}

    /**
     * this method draws towerA at x and y
     * 
     * @param Graphics g
     */
	public void draw(Graphics g) {
        Color transparentWhite = new Color(255, 60, 255, 90);
        g.setColor(transparentWhite);

        g.fillOval((int)this.x - 130, (int)this.y - 130, 300,300);

		g.drawImage(control.getImage("towerB.png"), this.x, this.y, null);	
	}
}

