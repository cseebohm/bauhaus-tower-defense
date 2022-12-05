package game;

import java.awt.Graphics;
import java.nio.file.Path;
import java.awt.Point;

public abstract class Enemy extends GameObject{

    Point currentLoc;

    @Override
    public void update(double timeElapsed) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        
    }

    public Point getLoc() {
        return currentLoc;
    }

    abstract public void takeDamage(int damage);
}
