/**
 * this class controls the tower defense game
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;

public class Control extends JPanel implements Runnable, ActionListener, MouseListener, MouseMotionListener {

	View view;
	State state;
	
	Path path;

    int mouseX;
    int mouseY;
    boolean mouseClick;

    BufferedImage background;
    BufferedImage enemyA;
    BufferedImage towerA; 
	
	public Control() 
	{
		//   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(this);
        
	}
	
	/**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
	public void run() 
	{	
        //load path
        loadPath();

        //load images
        loadImage();

        //create state and view objects
		state = new State();
		view = new View(this, state);
		
        //add mouse listeners to view
        view.addMouseListener(this);
        view.addMouseMotionListener(this);

        //initialize state
		state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(background));  // Add one background object to our list
        state.addGameObject(new EnemyA(path, enemyA, state));  // Add one enemy to our list
        state.addGameObject(new TowerAButton(towerA, this, state)); // Add towerA button
        state.addGameObject(new Menu(state));
        state.finishFrame();    // Mark the next frame as ready

        repaint();           // Draw it.
        
        //start the timer
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
        
	}
	
    /**
     * Method is called 16x/s after timer is started
     */
    public void actionPerformed(ActionEvent e) {

        state.startFrame();
        
        for (GameObject go : state.getFrameObjects())
            go.update(0);    
        
        state.finishFrame();
        view.repaint();

        mouseClick = false;
	}

	/**
     * Accessor method for the path
     */
	public Path getPath()
	{
		return this.path;
	}

    /**
     * Loads the path
     */
	public void loadPath()
	{
        try {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream pathStream = myLoader.getResourceAsStream("resources/path.txt");
            Scanner pathScanner = new Scanner(pathStream);
            path = new Path(pathScanner);
            }
            
            //should be IOException
            catch(Exception e) {
                System.out.println("Error loading path");
            }
	}

    /**
     * Loads the images
     */
	public void loadImage()
	{
        this.background = getImage("bauhausBackground.png");
        this.enemyA = getImage("enemyA.png");
        this.towerA = getImage("towerA.png");

        System.out.println("load images!");
    }

	/**
     * Load buffered image
     */
    public BufferedImage getImage (String filename)
    {
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            return image;
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out.
            return null;  // Does not happen, the application has exited.
        }
    }

    /**
     * method sets mouse position when it is moved
     */
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /** 
     * accessor method for x location of the mouse
     * 
     * @return mouseX
     */
    public int getMouseX(){
        return mouseX;
    }

    /**
     * accessor mehtod for y location of the mouse
     * 
     * @return mouseY
     */
    public int getMouseY(){
        return mouseY;
    }

    /**
     * method to track mouse clicks
     */
    public void mouseReleased(MouseEvent e) {
        this.mouseClick = true;
        
    }

    /**
     * accessor method for mouse clicks
     * @return boolean mouseClick
     */
    public boolean getClick(){
        return mouseClick;
    }

    //unnecessary methods from mouseListener and mouseMotionListener
    public void mouseDragged(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
