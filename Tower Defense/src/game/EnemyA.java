/**
 * this class defines enemyA 
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class EnemyA extends Enemy {
	
	private double percentage;
    private double velocity;

    Path path;
    
    private State state;
	private Control control;
    
    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param Path
     * @param BufferedImage
     */
    public EnemyA(double velocity, Path path, Control control, State state)
    {
    	this.percentage = 0;
        
        isVisible = true;
        isExpired = false;
        
        this.path = path;

        this.state = state;
        this.control = control;

        this.velocity = velocity;
    }

    /**
     * this method updates the percentage after a certain time has elapsed
     * 
     * @param double timeElapsed
     */
	public void update(double timeElapsed) {
		//move percentage by velocity of pixels per millisecond
            //start with 1 pixels/second

        double movePerFrame = this.velocity * state.getElapsedTime() * Math.pow(10, -12.0);
		percentage += movePerFrame / control.getPath().getLength();
		
		if(percentage >= (1)){
			//expire current enemy
            isExpired = true;

            //lose life
            state.changeLives(-1);
        }
	}

    /**
     * this method draws enemyA at the percentage along the path
     * 
     * @param Graphics g 
     */
	public void draw(Graphics g) {
		currentLoc = path.convertToCoordinates(percentage);
		g.drawImage(control.getImage("enemyA.png"), currentLoc.x - 10, currentLoc.y - 10, null);	
	}

    /**
     * this method sets the target to expired
     */
    public void setExpired() {
		isExpired = true;
        isVisible = false;

        state.changeMoney(50);
        state.changeScore(75);
	}

}
