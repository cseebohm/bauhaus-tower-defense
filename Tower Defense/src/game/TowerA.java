package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TowerA extends GameObject{

    BufferedImage image;
    
    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param control
     */
    public TowerA(BufferedImage towerA)
    {        
        isVisible = false;
        isExpired = false;

        this.image = towerA;
    }
    
	@Override
	public void update(double timeElapsed) {		
	}

	public void draw(Graphics g) {
		g.drawImage(image, 15, 150, null);	
	}

}
