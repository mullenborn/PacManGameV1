/**
 * Player.java
 * @author Nils Müllenborn, nimu@ruc.dk
 * Created on 28 March 2019
 * Simple object that contains an x, y and visited
 * and corrosponding getters and setters
 * Visited not used
 * (should be extended from position)
 */
package SnakeLogic;


public class Player {
    private int X;
    private int Y;
    private boolean isVisited;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setVisited(boolean v) {
        isVisited = v;
    }

    public boolean getVisited() {
        return isVisited;
    }

}
