/**
 * TowerAButton class draws and defines the button to buy tower type A
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
     * constructor for the tower A button 
     * 
     * @param towerA
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
     * update method for the towerA button, executes consumeClick after time elapsed
     * 
     * @param timeElapsed
     */
    public void update(double timeElapsed) { 
        consumeClick();   
    }

    /**
     * draw method for the towerA button
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

        //g.setColor(Color.BLACK);
        //g.setFont(new Font( "Serif", Font.PLAIN, 12));
        //String level = String.valueOf(state.getCurrentLevel() + 1);
        //g.drawString(level, x+9, y+16);

        g.setColor(Color.black);
		g.setFont(new Font( "Serif", Font.PLAIN, 9));
        g.drawString("ready", x, y+35);
    }
    
    /**
     * consume click method for tower A button, if the towerA button is clicked, add a towerA object and change money by -$250
     */
    public void consumeClick() {
        //if in towerA area and mouseClick == TRUE
        if(control.getMouseX()>560 && control.getMouseY()>120 && control.getMouseX()<586 && control.getMouseY()<146 && control.getClick())
            {
                //set level to next level
                state.nextLevel();
            }
    }
    
}

