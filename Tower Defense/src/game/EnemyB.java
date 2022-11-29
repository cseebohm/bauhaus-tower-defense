/**
 * this class defines enemyB 
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class EnemyB extends GameObject {
	
	private double percentage;
    private double velocity;

    Path path;
    
    private State state;
	private Control control;
    
    /**
     * this constructor makes one EnemyB object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param Path
     * @param BufferedImage
     */
    public EnemyB(double velocity, Path path, Control control, State state)
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

        double velocity = this.velocity; 
        double movePerFrame = velocity * state.getElapsedTime() * Math.pow(10, -12.0);
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
		Point p = path.convertToCoordinates(percentage);
		g.drawImage(control.getImage("enemyB.png"), p.x - 10, p.y - 10, null);	
	}
}