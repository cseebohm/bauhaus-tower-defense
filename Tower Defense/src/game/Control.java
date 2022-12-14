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
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;
import java.util.Map;

public class Control extends JPanel implements Runnable, ActionListener, MouseListener, MouseMotionListener {

	View view;
	State state;
	
	Path path;

    int mouseX;
    int mouseY;
    boolean mouseClick;

    private Map <String, BufferedImage> imageCache;

    Level level;
    double enemySpawnTime;

    Timer t;

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
        //build the map
        imageCache = new TreeMap <String, BufferedImage> ();

        //load path
        loadPath();

        //create state and view objects
		state = new State(this);
		view = new View(this, state);
		
        //add mouse listeners to view
        view.addMouseListener(this);
        view.addMouseMotionListener(this);

        //initialize state
		state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this, state));  // Add one background object to our list
        state.addGameObject(new TowerAButton(this, state)); // Add towerA button
        state.addGameObject(new TowerBButton(this, state)); // Add towerA button
        state.addGameObject(new StartWaveButton(this, state));
        state.addGameObject(new Menu(state));
        state.finishFrame();    // Mark the next frame as ready
        
        //start the timer
        t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();

        //initial level
        level = new Level(this, state);
	}
	
    /**
     * Method is called 16x/s after timer is started
     */
    public void actionPerformed(ActionEvent e) {
        state.startFrame();
        
        //check lives
        if(state.getLives() <= 0)
        {
            state.setGameOver(true);
            state.addGameObject(new GameOver(this, state));
        }

        //run each level
        if(state.getCurrentLevel() == 1)
            enemySpawnTime = level.runLevelOne(enemySpawnTime);

        else if(state.getCurrentLevel() == 2)
            enemySpawnTime = level.runLevelTwo(enemySpawnTime);

        else if(state.getCurrentLevel() == 3)
            enemySpawnTime = level.runLevelThree(enemySpawnTime);

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
     * Load buffered image
     */
    public BufferedImage getImage (String filename)
    {
        //check if image is in map
        if(imageCache.containsKey(filename))
            return imageCache.get(filename);
        
        //try catch to load image
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            System.out.println(filename);
            imageCache.put(filename, image);
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

    /**
     * checks to see if a value exists with key
     * 
     * @param key
     * @return boolean
     */
	public boolean containsKey(Object key) {
		return false;
	}

    /**
     * gets the value of for a specific key 
     * 
     * @param key
     * @return value
     */
	public Object get(Object key) {
		return null;
	}

    /**
     * puts in a paired key and value
     * 
     * @param key
     * @param value
     */
	public Object put(Object key, Object value) {
		return null;
	}

    /**
     * start the timer
     */
    public void startTimer(){
        t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
    }

    /**
     * stop the timer
     */
    public void stopTimer(){
        t.stop();
    }

    /**
     * initialize level
     */
    public void newLevel(){
        level = new Level(this, state);
    }
    
	
    //unnecessary methods from mouseListener and mouseMotionListener
    public void mouseDragged(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    //unnecessary methods from Map
    /*
    @Override public boolean isEmpty() {return false;}
    @Override public boolean containsValue(Object value) {return false; }
    @Override public Object remove(Object key) {return null;}
    @Override public void putAll(Map m) {}
    @Override public void clear() {}
    @Override public Set keySet() {return null;}
    @Override public Collection values() {return null;}
    @Override public Set entrySet() {return null;}
	@Override public int size() {return 0;}
    */

}
