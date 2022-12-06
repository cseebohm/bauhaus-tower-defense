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

        strength = 1;
        damageTaken = 0;
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
     * this method takes damage, when damageTaken > strength of the object, it is expired
     */
    
    public void takeDamage(int damage) {
		this.damageTaken += damage;

        if(damageTaken >= strength)
        {
            isExpired = true;
            isVisible = false;

            state.changeMoney(50);
            state.changeScore(75);
        }
	}

}
