/**
 * this class defines the levels
 * 
 * @author Clarissa Seebohm
 * @version 12-6-2022
 * 
 */

package game;

import path.Path;

public class Level {

    Control control;
    State state;

    Path path;
    Boolean flag;

    public Level(Control control, State state)
    {
        this.control = control;
        this.state = state;

        this.path = control.getPath();
        flag = true;
    }
    
    /**
     * runs level one, for level one 10 enemyA 
     *      enemies spawn every .75s
     *      enemies move at a rate of 1 pixel per second
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelOne(double enemySpawnTime) {
        if(flag)
        {
            System.out.println("ENEMIES SPAWNING!!");
            flag = false;
        }
            
        //makes 10 enemyA objects
        if(((state.getTotalTime() - enemySpawnTime) > .75 * Math.pow(10,3)) && state.getEnemyCount() < 10)
        {
        state.addGameObject(new EnemyA((1), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }

        return enemySpawnTime;
    }

    /**
     * runs level two, for level two, 10 enemyB, 5 enemyA 
     *      enemies spawn every .5 seconds
     *      enemies move at a rate of 1.25 pixels per second
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelTwo(double enemySpawnTime) {
            
        //makes 5 enemyA objects
        if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && state.getEnemyCount() < 5)
        {
            
        state.addGameObject(new EnemyA((1.25), path, control, state));
        state.changeEnemyCount(1);
                        
        enemySpawnTime = state.getTotalTime();
        
        }
        
        //makes 10 enemyB objects
        if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && state.getEnemyCount() < 15 && (state.getEnemyCount() >= 5))
        {
    
        state.addGameObject(new EnemyB((1.25), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }
    
        //makes 5 enemyA objects
        else if((state.getTotalTime() - enemySpawnTime) > (.5 * Math.pow(10,3)) && (state.getEnemyCount() < 20) && (state.getEnemyCount() >= 15))
        { 
            state.addGameObject(new EnemyA((1.25), path, control, state));
            state.changeEnemyCount(1);
                        
            enemySpawnTime = state.getTotalTime(); 
        }

        return enemySpawnTime;

    }

    /**
     * runs level three, for level three 15 enemyB, 10 enemyA, 10 enemy B
     *      enemies spawn every .35 seconds
     *      enemies move at a rate of 1.4 pixels per second
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelThree(double enemySpawnTime) {
            
        //makes 15 enemyB objects
        if(((state.getTotalTime() - enemySpawnTime) > .35 * Math.pow(10,3)) && state.getEnemyCount() < 15)
        {
    
        state.addGameObject(new EnemyB((1.4), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }

        //make 10 enemyA objects
        else if(((state.getTotalTime() - enemySpawnTime) > .35 * Math.pow(10,3)) && (state.getEnemyCount() < 25) && (state.getEnemyCount() >= 15))
        {
            
        state.addGameObject(new EnemyA((1.4), path, control, state));
        state.changeEnemyCount(1);
                        
        enemySpawnTime = state.getTotalTime();
        
        }

        //make 10 enemyB objects
        else if(((state.getTotalTime() - enemySpawnTime) > .35 * Math.pow(10,3)) && (state.getEnemyCount() < 35) && (state.getEnemyCount() >= 25))
        {
        state.addGameObject(new EnemyB((1.4), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();
        }
        return enemySpawnTime;

    }
    
}
