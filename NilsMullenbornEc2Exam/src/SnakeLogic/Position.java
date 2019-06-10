/**
 * Position.java
 * @author Nils Müllenborn, nimu@ruc.dk
 * Created on 28 March 2019
 * Simple object that contains an x and y
 * and corrosponding getters and setters
 */
package SnakeLogic;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
