/**
 * this class holds the current state of the tower defense game
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package game;

import java.util.ArrayList;
import java.util.List;

public class State {
	
	List<GameObject> currentFrameGameObjects;
    List<GameObject> nextFrameGameObjects;

    int lives;
    int money;
    int score;
	
    /**
     * this constructor creates an empty list for the current frame
     * 
     */
    public State()
    {
        currentFrameGameObjects = new ArrayList<GameObject>();

        //initialize lives to 3 and money to 500
        lives = 3;
        money = 5000;
        score = 0;
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
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
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
        //nextFrameGameObjects = null;  // I added this -- it makes it clear there is only a current list now.
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
        this.lives += lifeChange;

        if(lives < 0)
            System.out.println("Game Over!");
        else
            System.out.println("# of Lives: " + lives);
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
}
