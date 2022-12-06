package game;

import java.awt.Graphics;
import java.awt.Point;
import path.Path;

public abstract class Enemy extends GameObject{

    Point currentLoc;

    protected double percentage;
    protected double velocity;

    protected Path path;
    
    protected State state;
	protected Control control;

    protected int strength;
    protected int damageTaken;

    /**
     * this method updates the percentage after a certain time has elapsed
     * 
     * @param double timeElapsed
     */
	public void update(double timeElapsed) {
		//move percentage by velocity of pixels per millisecond
        double movePerFrame = this.velocity * state.getElapsedTime() * Math.pow(10, -12.0);
		percentage += movePerFrame / control.getPath().getLength();
		
		if(percentage >= (1)){
			//expire current enemy
            isExpired = true;

            //lose life
            state.changeLives(-1);
        }
	}

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        
    }

    public Point getLoc() {
        return currentLoc;
    }

    abstract public void takeDamage(int damage);
}
