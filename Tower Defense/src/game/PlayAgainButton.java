/**
 * StartWaveButton class draws and defines the button to run the level
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class PlayAgainButton extends GameObject implements Clickable{
    protected GameOver gameOver;
    
    /**
     * constructor for the start wave button 
     * 
     * @param control
     * @param state
     */
    public PlayAgainButton(Control control, State state, GameOver gameOver){
        isVisible = true;
        isExpired = false;

        this.control = control;
        this.state = state;

        this.gameOver = gameOver;
    }

    /**
     * update method for the start wave button, executes consumeClick after time elapsed
     * 
     * @param timeElapsed
     */
    public void update(double timeElapsed) { 
        consumeClick();   
    }

    /**
     * draw method for the start wave button
     * 
     * @param timeElapsed
     */
    public void draw(Graphics g) {
        //location of button
        int x = 470;
        int y = 120;
        
        //draw button
        g.setColor(Color.white);
        g.fillRoundRect(x, y, 26*3, 26, 5,5);

        g.setColor(Color.black);
        g.setFont(new Font( "CooperBlackStd-Italic", Font.PLAIN, 12));
        g.drawString("level", x, y+35);
    }
    
    /**
     * consume click method for start wave button
     */
    public void consumeClick() {
        //if in area and mouseClick == TRUE
        if(control.getMouseX()>470 && control.getMouseY()>80 && control.getMouseX()<550 && control.getMouseY()<106 && control.getClick())
            {
                control.stopTimer();

                //reset state
                state.reset();

                //remove the game objects
                state.removeGameObject(this);
                state.removeGameObject(gameOver);

                control.startTimer();
            }
    }
    
}
