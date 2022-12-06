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

public class TowerAButton extends GameObject implements Clickable{

    Control control;
    State state;

    /**
     * constructor for the tower A button 
     * 
     * @param towerA
     * @param control
     * @param state
     */
    public TowerAButton(Control control, State state){
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
        int x = 460;
        int y = 120;
        
        //draw button
        g.setColor(Color.white);
        g.fillRoundRect(x, y, 26, 26, 5,5);

        g.drawImage(control.getImage("towerA.png"), x+3, y+3, 20,20, null);

        g.setColor(Color.black);
		g.setFont(new Font( "Serif", Font.PLAIN, 9));
        g.drawString("$250", x, y+35);
    }
    
    /**
     * consume click method for tower A button, if the towerA button is clicked, add a towerA object and change money by -$250
     */
    public void consumeClick() {
        //if in towerA area and mouseClick == TRUE
        if(control.getMouseX()>460 && control.getMouseY()>120 && control.getMouseX()<486 && control.getMouseY()<146 && control.getClick())
            {
                //only buy tower if money > $250
                if(state.getMoney()>=250)
                {
                    //add tower and charge money
                    state.addGameObject(new TowerA(control, state));
                    state.changeMoney(-250);
                }
            }
    }
    
}
