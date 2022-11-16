/**
 * this class defines the background 
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends GameObject{
	
	BufferedImage background;
	
	/**
	 * this constructor makes a background object
	 * 
	 * @param BufferedImage Background
	 */
	public Background(BufferedImage background)
	{
		isVisible = true;
        isExpired = false;

		this.background = background;
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
		g.drawImage(background, 0, 0, null);
	}

}
