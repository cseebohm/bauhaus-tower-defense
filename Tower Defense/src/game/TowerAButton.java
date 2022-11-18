package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class TowerAButton extends GameObject implements Clickable{

    Control control;
    State state;
    BufferedImage image;

    public TowerAButton(BufferedImage towerA, Control control, State state){
        isVisible = true;
        isExpired = false;

        this.image = towerA;
        this.control = control;
        this.state = state;
    }

    public void update(double timeElapsed) { 
        consumeClick();   
    }

    public void draw(Graphics g) {
        int x = 450;
        int y = 120;
        
        g.setColor(Color.white);
        g.fillRoundRect(x, y, 26, 26, 5,5);

        g.drawImage(image, x+3, y+3, 20,20, null);

        g.setColor(Color.black);
        Font groovyFont9 = new Font( "CooperBlackStd-Italic", Font.PLAIN, 9);
		g.setFont(groovyFont9);
        g.drawString("tower a", x-7, y+35);
    }
    
    public void consumeClick() {
        if(control.getMouseX()>450 && control.getMouseY()>120 && control.getMouseX()<470 && control.getMouseY()<140 && control.getClick())
            {
                if(state.getMoney()>=250)
                {
                    state.addGameObject(new TowerA(image, control, state)); // Add towerA button
                    state.changeMoney(-250);
                }
            }
    }
    
}
