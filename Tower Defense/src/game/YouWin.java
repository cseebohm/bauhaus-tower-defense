/**
 * this class defines the game over background 
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class YouWin extends GameObject{
	
	private State state;
	private Control control;
	
	/**
	 * this constructor makes a background object
	 * 
	 * @param BufferedImage Background
	 */
	public YouWin (Control control, State state)
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
		g.drawImage(control.getImage("youwin.png"), 0, 0, null);
		
		g.setColor(Color.white);

		//draw game state
		g.setFont(new Font( "Serif", Font.BOLD, 11));
		g.drawString(("SCORE: " + state.getScore()), 475,50);
	}

}
