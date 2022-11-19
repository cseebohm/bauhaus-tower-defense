/**
 * this class displays the tower defense game
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{

	State state;
	Control control;
	
	/**
     * this constructor creates a View object given a state and control object, this is where the JFrame is defined
     *
     * @param Control control
     * @param State state
     */
	public View(Control control, State state) 
	{
		this.control = control;
		this.state = state;
		
		// make JFrame
		JFrame f = new JFrame("bauhaus.tower.defense");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		// set sizes
		f.setMinimumSize(new Dimension(600, 600));
		f.setPreferredSize(new Dimension(600, 600));
		        
		// set content
		f.setContentPane(this); 
		        
		// pack and display JFrame
		f.pack();
		f.setVisible(true);
	}
	
	/**
     * Draws everything
     *
     * @param g: the Graphics object to draw to
     */
    public void paint (Graphics g)
    {	
    	for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired())
                go.draw(g);
    }
}
