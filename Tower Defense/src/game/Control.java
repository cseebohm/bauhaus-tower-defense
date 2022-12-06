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

import java.awt.Color;

public class Control extends JPanel implements Runnable, ActionListener, MouseListener, MouseMotionListener {

	View view;
	State state;
	
	Path path;

    int mouseX;
    int mouseY;
    boolean mouseClick;

    private Map <String, BufferedImage> imageCache;

    boolean levelOne;
    double enemySpawnTime;

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
        state.addGameObject(new Menu(state));
        state.finishFrame();    // Mark the next frame as ready
        
        //start the timer
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();

        //initial level
        levelOne = true;
        enemySpawnTime = 0;

        //get pixel color test

        Color test = state.getPixelColor(0, 0);
        System.out.print(test);
        
	}
	
    /**
     * Method is called 16x/s after timer is started
     */
    public void actionPerformed(ActionEvent e) {

        state.startFrame();
        
        //check lives
        if(state.getLives() <= 0 || state.isGameOver())
        {
            state.setGameOver(true);
            state.addGameObject(new GameOver(this, state));
            
        }

        enemySpawnTime = levelOne(enemySpawnTime);

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
     * runs level one, for level one 5 enemyA objects spawn every .75s, then 3 enemyB objects spawn every .5s
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double levelOne(double enemySpawnTime) {
            
        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        if(((state.getTotalTime() - enemySpawnTime) > .75 * Math.pow(10,3)) && state.getEnemyCount() < 6)
        {
    
        state.addGameObject(new EnemyA((1), path, this, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }
    
        //makes 2 enemyBs at every .5 seconds that move at a rate of 1.25 pixel per second
        else if((state.getTotalTime() - enemySpawnTime) > (.5 * Math.pow(10,3)) && (state.getEnemyCount() < 9) && (state.getEnemyCount() >= 6)) 
        {
            state.addGameObject(new EnemyB(1, path, this, state));
            state.changeEnemyCount(1);
                
            enemySpawnTime = state.getTotalTime();
        }

        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        else if(((state.getTotalTime() - enemySpawnTime) > .75 * Math.pow(10,3)) && (state.getEnemyCount() < 12) && (state.getEnemyCount() >= 9))
        {
            
        state.addGameObject(new EnemyA((1), path, this, state));
        state.changeEnemyCount(1);
                        
        enemySpawnTime = state.getTotalTime();
        
        }

        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        else if(((state.getTotalTime() - enemySpawnTime) > .75 * Math.pow(10,3)) && (state.getEnemyCount() < 15) && (state.getEnemyCount() >= 12))
        {
        state.addGameObject(new EnemyA((1), path, this, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();
        }
        return enemySpawnTime;

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
