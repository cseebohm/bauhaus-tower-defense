/**
 * this class defines the background 
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.Graphics;

public class Background extends GameObject{
	
	Control control;
	
	/**
	 * this constructor makes a background object
	 * 
	 * @param control
	 */
	public Background(Control control)
	{
		isVisible = true;
        isExpired = false;
        
        this.control = control;
	}

	@Override
	public void update(double timeElapsed) {
		//update method not used for background
		
	}

	/**
	 * this draw method draws the background 
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(control.getImage("bauhausBackground.png"), 0, 0, null);
	}

}
