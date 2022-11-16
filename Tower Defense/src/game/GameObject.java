package game;

import java.awt.Graphics;

public abstract class GameObject {

	protected boolean isVisible;
	protected boolean isExpired;
	
	public boolean isVisible() 
	{
		return isVisible;
	}
	
	public boolean isExpired()
	{
		return isExpired;
	}
	
	abstract public void update(double timeElapsed);
	abstract public void draw (Graphics g);
}
