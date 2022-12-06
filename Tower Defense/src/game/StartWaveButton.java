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

public class StartWaveButton extends GameObject implements Clickable{
    /**
     * constructor for the start wave button 
     * 
     * @param control
     * @param state
     */
    public StartWaveButton(Control control, State state){
        isVisible = true;
        isExpired = false;

        this.control = control;
        this.state = state;
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
        int x = 560;
        int y = 120;
        
        //draw button
        g.setColor(Color.white);
        g.fillRoundRect(x, y, 26, 26, 5,5);

        g.setColor(state.getPixelColor(300, 400));
        g.setFont(new Font( "CooperBlackStd", Font.PLAIN, 16));
        String level = String.valueOf(state.getCurrentLevel());
        g.drawString(level, x+7, y+17);

        g.setColor(Color.black);
		g.setFont(new Font( "Serif", Font.PLAIN, 9));
        g.drawString("level", x, y+35);
    }
    
    /**
     * consume click method for start wave button
     */
    public void consumeClick() {
        //if in area and mouseClick == TRUE
        if(control.getMouseX()>560 && control.getMouseY()>120 && control.getMouseX()<586 && control.getMouseY()<146 && control.getClick())
            {
                //set level to next level
                state.nextLevel();
            }
    }
    
}

