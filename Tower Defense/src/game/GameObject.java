/**
 * abstract game object class defines requirements for drawable objects
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;

public abstract class GameObject {

	protected boolean isVisible;
	protected boolean isExpired;

	protected State state;
	protected Control control;
	
	
	/**
	 * isVisible accessor method
	 * 
	 * @return boolean isVisible
	 */
	public boolean isVisible() 
	{
		return isVisible;
	}
	
	/**
	 * isExpired accessor method
	 * 
	 * @return boolean isExpired
	 */
	public boolean isExpired()
	{
		return isExpired;
	}

	/**
	 * setExpired mutator method
	 * 
	 * @return boolean isExpired
	 */
	public void setExpired()
	{
		this.isExpired = true;
	}
	
	/**
	 * require update method with input of time elased
	 * 
	 * @param timeElapsed
	 */
	abstract public void update(double timeElapsed);

	/**
	 * require draw method with input of graphics g
	 * 
	 * @param g
	 */
	abstract public void draw (Graphics g);
}
