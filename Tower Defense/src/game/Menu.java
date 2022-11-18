package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import game.State;

public class Menu extends GameObject{

    State state;

    public Menu(State state){
        this.state = state;

        isVisible = true;
        isExpired = false;
    }

    @Override
    public void update(double timeElapsed) {
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        
        Font groovyFont24 = new Font( "CooperBlackStd-Italic", Font.PLAIN, 22);
		g.setFont(groovyFont24);
        g.drawString("Bahaus", 490, 30);

        Font groovyFont9 = new Font( "CooperBlackStd", Font.PLAIN, 11);
		g.setFont(groovyFont9);
        g.drawString("tower defense", 505, 42);

        Font groovyFont14 = new Font( "CooperBlackStd-Italic", Font.PLAIN, 14);
		g.setFont(groovyFont14);
        g.drawString(("score: " + state.getScore()), 483,65);
        g.drawString(("lives: " + state.getLives()), 476,80);
        g.drawString(("money: $" + state.getMoney()), 469,95);
    }
}
