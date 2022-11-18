/**
 * this class defines the menu object which displays the title, score, lives, and money
 * 
 * @author  Clarissa Seebohm
 * @version November 17, 2022
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Menu extends GameObject{

    State state;

    /**
     * Constructor for the menu object, which displays the title, score, lives, and money
     * 
     * @param state
     */
    public Menu(State state){
        this.state = state;

        isVisible = true;
        isExpired = false;
    }

    /**
     * this method draws the menu object
     * 
     * @param Graphics g 
     */
    public void draw(Graphics g) {
        //set color to black
        g.setColor(Color.black);
        
        //draw title
		g.setFont(new Font( "CooperBlackStd-Italic", Font.PLAIN, 22));
        g.drawString("Bahaus", 490, 30);

		g.setFont(new Font( "CooperBlackStd", Font.PLAIN, 11));
        g.drawString("tower defense", 505, 42);

        //draw game state
		g.setFont(new Font( "CooperBlackStd-Italic", Font.PLAIN, 14));
        g.drawString(("score: " + state.getScore()), 483,65);
        g.drawString(("lives: " + state.getLives()), 476,80);
        g.drawString(("money: $" + state.getMoney()), 469,95);
    }

    //unused methods
    public void update(double timeElapsed) {}

}
