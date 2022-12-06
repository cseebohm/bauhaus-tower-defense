/**
 * this class defines the background 
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.Graphics;


public class Background extends GameObject{
	
	/**
	 * this constructor makes a background object
	 * 
	 * @param BufferedImage Background
	 */
	public Background(Control control, State state)
	{
		isVisible = true;
        isExpired = false;

		this.state = state;
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
		g.drawImage(control.getImage("bauhausBackground.png"), 0, 0, null);
	}

}
