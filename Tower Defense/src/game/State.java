/**
 * this class holds the current state of the tower defense game
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Color;

public class State {
	
	protected List<GameObject> currentFrameGameObjects;
    protected List<GameObject> nextFrameGameObjects;

    protected int lives;
    protected int money;
    protected int score;

    protected int enemyCount;

    protected boolean isGameOver;

    protected double elapsedTime;
    protected double totalTime;

    protected double startTime;
    protected double currentTime;

    protected Control control;

    protected int currentLevel;
	
    /**
     * this constructor creates an empty list for the current frame
     * 
     */
    public State(Control control)
    {
        currentFrameGameObjects = new ArrayList<GameObject>();

        //initialize lives to 3 and money to 500
        lives = 3;
        money = 500;
        score = 0;

        enemyCount = 0;
        totalTime = 0;
        
        isGameOver = false;

        startTime = System.currentTimeMillis();

        this.control = control;

        currentLevel = 0;
    }
    

    /**
     * this accessor returns the list of game objects for the current frame
     * 
     * @return List<GameObject> currentFrameGameObjects
     */
    public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }
    
    /**
     * When a frame of animation starts in our game, we'll start preparing the 'next' frame's game object list.  
     * This helper method sets up the next frame's list to be a copy of the current list. 
     * The Control class will call this method once at the start of every animation frame.
     * 
     */
    public void startFrame ()
    {
        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list

        this.currentTime = System.currentTimeMillis();
        this.totalTime = this.currentTime - this.startTime;
        this.elapsedTime = this.currentTime - this.totalTime;    
    }
    
    /**
     * When a frame ends, the current frame list should go away and be replaced by the list we prepared 
     * for the next frame. The Control class will call this method at the end of every animation frame.
     */
    public void finishFrame ()
    {
        for (GameObject go : getFrameObjects())
            if(go.isExpired == true)
                //remove object
                removeGameObject(go);

        currentFrameGameObjects = nextFrameGameObjects;
    }
    
    /**
     * a helper method for adding game objects to the next frame of the game.
     * 
     * @param go
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }
    
	/**
     * a helper method for removing game objects to the next frame of the game.
     * 
     * @param go
     */
    public void removeGameObject (GameObject go)
    {
        nextFrameGameObjects.remove(go);
    }
	
    /**
     * an accessor method for lives
     * 
     * @return lives
     */
    public int getLives () {
        return this.lives;
    }

    /**
     * a mutator method for lives
     * 
     * @param lifeChange
     */
    public void changeLives (int lifeChange)
    {
        if(lives + lifeChange > 0)
            this.lives += lifeChange;
        else
            this.lives = 0;
    }
    
    /**
     * an accessor method for money
     * 
     * @return money
     */
    public int getMoney ()
    {
        return this.money;
    }

    /**
     * a mutator method for money
     * 
     * @param moneyChange
     */
    public void changeMoney (int moneyChange)
    {
        if(money+moneyChange >= 0)
            this.money += moneyChange;
    }

    /**
     * an accessor method for score
     * 
     * @return score
     */
    public int getScore ()
    {
        return this.score;
    }

    /**
     * a mutator method for score
     * 
     * @param int 
     */
    public void changeScore (int scoreChange)
    {
        this.score += scoreChange;
    }

    /**
     * an accessor method for enemyCount
     * 
     * @return score
     */
    public int getEnemyCount ()
    {
        return this.enemyCount;
    }

    /**
     * a mutator method for score
     * 
     * @param int 
     */
    public void changeEnemyCount (int enemyChange)
    {
        this.enemyCount += enemyChange;
    }

    /**
     * a mutator method for score
     * 
     * @param int 
     */
    public void setEnemyCount (int enemy)
    {
        this.enemyCount = enemy;
    }

    /**
     * a mutator method for isGameOver
     * 
     * @param boolean
     */
    public void setGameOver (boolean isOver)
    {
        this.isGameOver = isOver;
    }

    /**
     * an accessor method for isGameOver
     * 
     * @param boolean
     */
    public boolean isGameOver ()
    {
        return this.isGameOver;
    }

    /**
     * an accessor method for elapsedTime
     * 
     * @return elapsedTime
     */
    public double getElapsedTime ()
    {
        return this.elapsedTime;
    }

    /**
     * an accessor method for totalTime
     * 
     * @return elapsedTime
     */
    public double getTotalTime ()
    {
        return this.totalTime;
    }

    /**
     * finds the nearest enemy to a point
     * 
     * @return Enemy
     */
    public Enemy findNearestEnemy (Point p)
    {
        Enemy closestEnemy = null;
        double distanceE;
        double distanceMin = 600;

        for(GameObject a: currentFrameGameObjects)
        {    
            if (a instanceof Enemy)
            {
            Enemy e = (Enemy) a;
            // Do work with the enemy, e, here.
            
            distanceE = getDistance(e.getLoc(), p);

                if (distanceE < distanceMin)
                {
                    closestEnemy = e;
                    distanceMin = distanceE;
                }
            }
        }

        return closestEnemy;
    }

    /**
     * finds the distance between two points
     * 
     * @return double
     */
    public double getDistance(Point a, Point b)
    {
        double distance;
            
        distance = (Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y)));

        return distance;
    }

    /**
     * this method returns the color of the background at a certain point x,y
     * 
     * @param x
     * @param y
     * @return Color
     */
    public Color getPixelColor(int x, int y){

		java.awt.image.BufferedImage background = control.getImage("bauhausBackground.png");

        int pixel = background.getRGB(x,y);

        //Creating a Color object from pixel value
        Color color = new Color(pixel, true);

        return color;
    }

    /**
     * this method returns the color of the path
     * 
     * @return Color
     */
    public Color getPathColor(){

		Color black = getPixelColor(0, 300);

        return black;
    }

    /**
     * an accessor method for currentLevel
     * 
     * @return level
     */
    public int getCurrentLevel () {
        return this.currentLevel;
    }

    /**
     * a mutator method for currentLevel
     * 
     */
    public void setCurrentLevel (int i) {
        this.currentLevel = i;
    }

    /**
     * a mutator method for currentLevel
     * 
     */
    public void nextLevel () {
        this.currentLevel = this.currentLevel + 1;
        setEnemyCount(0);

        if(currentLevel > 3){
            changeScore(getMoney());
            addGameObject(new GameOver(control, this));
        }
    }
}
