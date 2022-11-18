package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TowerA extends GameObject implements Clickable{

    BufferedImage image;
    Control control;
    State state;
    
    boolean isMoving;

    int x;
    int y;

    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     */
    public TowerA(BufferedImage towerA, Control control, State state)
    {        
        isVisible = true;
        isExpired = false;
        isMoving = true;

        this.image = towerA;
        this.control = control;
        this.state = state;

        this.x = control.getMouseX();
        this.y = control.getMouseY();
    }
    
	public void update(double timeElapsed) {
        if(isMoving){
            this.x = control.getMouseX();
            this.y = control.getMouseY();
            consumeClick();
        }		
	}

	public void draw(Graphics g) {
		g.drawImage(image, this.x, this.y, null);	
	}

    public void consumeClick() {
        if(control.getClick())
            isMoving=false;
    }

}
