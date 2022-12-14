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

public class GameOver extends GameObject{
	
	private State state;
	private Control control;
	
	/**
	 * this constructor makes a background object
	 * 
	 * @param BufferedImage Background
	 */
	public GameOver (Control control, State state)
	{
		isVisible = true;
        isExpired = false;

		this.state = state;
		this.control = control;

		state.setHighScore();
		state.addGameObject(new PlayAgainButton(control, state, this));

		for(GameObject go: state.nextFrameGameObjects)
                    if(go instanceof Enemy)
                        go.setExpired();
                    else if(go instanceof Tower)
                        go.setExpired();
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
		if(state.getLives() < 1)
			g.drawImage(control.getImage("gameover.png"), 0, 0, null);
		else
			g.drawImage(control.getImage("youwin.png"), 0, 0, null);
		
		g.setColor(Color.white);
		
		//draw score
		g.setFont(new Font( "Serif", Font.BOLD, 11));
		g.drawString(("SCORE: " + state.getScore()), 475,65);

		//draw score
		g.setFont(new Font( "Serif", Font.BOLD, 11));
		g.drawString(("HIGH SCORE: " + state.getHighScore()), 475,50);

		//location of button
		int x = 475;
		int y = 80;
				
		//draw play again button button
		g.setColor(Color.white);
		g.fillRoundRect(x, y, 26*3, 26, 5,5);
		
		g.setColor(Color.black);
		g.setFont(new Font( "CooperBlackStd-Italic", Font.PLAIN, 11));
		g.drawString("play again?", x+5, y+15);
	}

}
