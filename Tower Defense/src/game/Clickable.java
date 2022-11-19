/**
 * Clickable interface for clickable objects
 * 
 * @author  Clarissa Seebohm
 * @version November 18, 2022
 */
package game;

public interface Clickable {

    /**
     * requires consumeClick method for object specific action when clicked
     */
    abstract public void consumeClick();
}

