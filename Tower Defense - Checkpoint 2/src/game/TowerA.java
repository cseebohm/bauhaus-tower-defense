package game;

import java.awt.Graphics;

public class TowerA extends GameObject{

    Control control;
    
    /**
     * this constructor makes one EnemyA object, the default percentage is zero
     * the enemy is visible and not expired
     * 
     * @param control
     */
    public TowerA(Control control)
    {        
        isVisible = true;
        isExpired = false;
        
        this.control = control;
    }
    
	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(control.getImage("towerA1.png"), 15, 150, null);	
	}

}
