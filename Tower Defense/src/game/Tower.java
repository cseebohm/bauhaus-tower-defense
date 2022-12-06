package game;

import java.awt.Graphics;
import java.awt.Point;

public class Tower extends GameObject implements Clickable{

    protected boolean isMoving;

    protected int x;
    protected int y;

    protected Enemy target;
    protected Enemy currentTarget;

    protected Point currentLoc;

    protected double timeFired;

    @Override
    public void update(double timeElapsed) {  
    }

    @Override
    public void draw(Graphics g) {
    }

    public void placeTower(){
        if(isMoving){
            this.x = control.getMouseX()-20;
            this.y = control.getMouseY()-20;
            consumeClick();
        }	
    }

    /**
     * this method is executed when isMoving is true in the update method
     * all it does is change isMoving to false after a click
     * 
     */
    public void consumeClick() {
        if(control.getClick())
        {
            isMoving=false;

            //define placed location
            currentLoc = new Point(this.x,this.y);
        }

        //out of bounds, dont place tower
        if(this.x<0 || this.x>600 || this.y<0 || this.y>600)
            isMoving=true;
        
        //if same color as path, dont place tower
        else if(state.getPixelColor(this.x, this.y).equals(state.getPathColor()))
            isMoving = true;
    }
    
}
