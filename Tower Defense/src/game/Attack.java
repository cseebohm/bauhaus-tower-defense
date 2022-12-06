/**
 * this class defines the attack superclass 
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

import java.awt.Graphics;
import path.Path;

public class Attack extends GameObject{

    protected int x,y;

    protected Path effectPath;

    protected double percentage;
    protected double velocity;

    protected Enemy target;

    @Override
    public void update(double timeElapsed) { }

    @Override
    public void draw(Graphics g) { }
    


}
