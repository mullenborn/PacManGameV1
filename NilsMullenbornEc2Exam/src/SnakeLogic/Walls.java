/**
 * Walls.java
 * @author Nils Müllenborn, nimu@ruc.dk
 * Created on 28 March 2019
 * Defines what a wall has and can do
 */
package SnakeLogic;

import javafx.scene.paint.Color;

public class Walls {
    private int x;
    private int y;
    private Color color;

    public Walls(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
