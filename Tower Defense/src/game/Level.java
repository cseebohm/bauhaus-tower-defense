package game;

import path.Path;

public class Level {

    Control control;
    State state;

    Path path;

    public Level(Control control, State state)
    {
        this.control = control;
        this.state = state;

        this.path = control.getPath();
    }
    
    /**
     * runs level one, for level one 5 enemyA objects spawn every .75s, then 3 enemyB objects spawn every .5s
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelOne(double enemySpawnTime) {
            
        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        if(((state.getTotalTime() - enemySpawnTime) > .75 * Math.pow(10,3)) && state.getEnemyCount() < 10)
        {
    
        state.addGameObject(new EnemyA((1), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }

        return enemySpawnTime;
    }

    /**
     * runs level two, for level one 5 enemyA objects spawn every .75s, then 3 enemyB objects spawn every .5s
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelTwo(double enemySpawnTime) {
            
        //makes 5 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && state.getEnemyCount() < 5)
        {
    
        state.addGameObject(new EnemyB((1.25), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }
    
        //makes 5 enemyBs at every .75 seconds that move at a rate of 1.25 pixel per second
        else if((state.getTotalTime() - enemySpawnTime) > (.5 * Math.pow(10,3)) && (state.getEnemyCount() < 10) && (state.getEnemyCount() >= 5)) 
        {
            state.addGameObject(new EnemyB(1.25, path, control, state));
            state.changeEnemyCount(1);
                
            enemySpawnTime = state.getTotalTime();
        }

        //makes 5 enemyAs at every .75 seconds that move at a rate of 1.25 pixel per second
        else if((state.getTotalTime() - enemySpawnTime) > (.5 * Math.pow(10,3)) && (state.getEnemyCount() < 15) && (state.getEnemyCount() >= 10))
        { 
            state.addGameObject(new EnemyA((1.25), path, control, state));
            state.changeEnemyCount(1);
                        
            enemySpawnTime = state.getTotalTime(); 
        }

        return enemySpawnTime;

    }

    /**
     * runs level three, for level one 5 enemyA objects spawn every .75s, then 3 enemyB objects spawn every .5s
     * 
     * @param enemySpawnTime
     * @return enemySpawnTime
     */
    public double runLevelThree(double enemySpawnTime) {
            
        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && state.getEnemyCount() < 10)
        {
    
        state.addGameObject(new EnemyB((1.25), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();

        }
    
        //makes 2 enemyBs at every .5 seconds that move at a rate of 1.25 pixel per second
        else if((state.getTotalTime() - enemySpawnTime) > (.5 * Math.pow(10,3)) && (state.getEnemyCount() < 15) && (state.getEnemyCount() >= 10)) 
        {
            state.addGameObject(new EnemyB(1.25, path, control, state));
            state.changeEnemyCount(1);
                
            enemySpawnTime = state.getTotalTime();
        }

        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        else if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && (state.getEnemyCount() < 20) && (state.getEnemyCount() >= 15))
        {
            
        state.addGameObject(new EnemyA((1.25), path, control, state));
        state.changeEnemyCount(1);
                        
        enemySpawnTime = state.getTotalTime();
        
        }

        //makes 4 enemyAs at every .75 seconds that move at a rate of 1 pixel per second
        else if(((state.getTotalTime() - enemySpawnTime) > .5 * Math.pow(10,3)) && (state.getEnemyCount() < 25) && (state.getEnemyCount() >= 20))
        {
        state.addGameObject(new EnemyB((1.25), path, control, state));
        state.changeEnemyCount(1);
                
        enemySpawnTime = state.getTotalTime();
        }
        return enemySpawnTime;

    }
    
}
