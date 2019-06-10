/**
 * Controller.java
 * @author Nils Müllenborn, nimu@ruc.dk
 * Created on 28 March 2019
 * Main controller of game including GUI
 */
package SnakeGUI;
import SnakeLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.*;

public class Controller {
    @FXML Canvas canvas;
    @FXML Label label1;
    @FXML Button btnClose, btnStart;
    private double fieldHeight, fieldWidth;
    private int widthCellNum, heightCellNum;
    private float refreshRate = 300; // previously 300
    private int[][] mazeMap, dfsMap;
    private Random random = new Random();
    private Player ghostOne, ghostTwo, ghostThree, pacMan;
    private DepthFirstAlgorithm dfsOne, dfsTwo, dfsThree;
    private Maze mazerator = new Maze();
    //private GraphicsContext gc;
    ArrayList<Walls> walls = new ArrayList<Walls>();

    /**
     * Executed when JavaFX is initialized.
     * Used to setup the Snake game
     */
    public void initialize() {
        // get mazeMap array
        mazeMap = mazerator.generateMazeThree();
        dfsMap = mazerator.generateMazeThree();

        // setup players
        ghostOne = new Player();
        ghostTwo = new Player();
        ghostThree = new Player();
        pacMan = new Player();

        calculateFields();
        AddWalls();

        // this position is used by ghostOne
        getStartPosition();

        // Start and control game loop
        new AnimationTimer() {
            long lastUpdate;

            public void handle(long now) {
                if (now > lastUpdate + refreshRate * 1000000) {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();
    }

    /**
     * Executed in initialize method
     * draws walls in mazeMap
     */
    private void AddWalls(){
        for (int x = 0; x < mazeMap.length; x++) {
            for (int y = 0; y < mazeMap[0].length; y++) {
                if (mazeMap[x][y] == 1){
                    walls.add(new Walls(Color.BLUE, x,y));
                }
            }
        }
    }

    /**
     * timer
     * Start and control game loop
     */
    private void gameTimer() {
        refreshRate = 300;
        new AnimationTimer()
        {
            long lastUpdate;

            public void handle(long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }
            }
        }.start();
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     * randomRambler provides random movement for ghostOne
     */
    private void update(long now) {

        dfsOne = new DepthFirstAlgorithm();
        dfsTwo = new DepthFirstAlgorithm();
        dfsThree = new DepthFirstAlgorithm();

        dfsOne.depthFirstAlgorithm(dfsMap, ghostOne, pacMan);
        dfsMap = mazerator.generateMazeThree();

        dfsTwo.depthFirstAlgorithm(dfsMap, ghostTwo, pacMan);
        dfsMap = mazerator.generateMazeThree();

        dfsThree.depthFirstAlgorithm(dfsMap, ghostThree, pacMan);
        dfsMap = mazerator.generateMazeThree();

        drawCanvas();
    }

    /**
     * get starts positions
     * set start positions for all movable items
     * NOTE: they must be within the limits of the maze chosen
     */
    private void getStartPosition() {
        ghostOne.setX(3);
        ghostOne.setY(16);
        ghostTwo.setX(10);
        ghostTwo.setY(10);
        ghostThree.setX(17);
        ghostThree.setY(4);
        pacMan.setX(1);
        pacMan.setY(1);
    }

    /**
     * Calculate heightCellNum and widthCellNum of each field
     * uses canvas heightCellNum and number of fields
     */
    private void calculateFields() {
        widthCellNum = mazeMap[0].length;
        heightCellNum = mazeMap.length;
        this.fieldHeight = canvas.getHeight() / this.heightCellNum;
        this.fieldWidth = canvas.getWidth() / this.widthCellNum;
    }

    /**
     * Draw the canvas - used in the gameloop
     * draws all items from the maze
     */
    private void drawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, widthCellNum * fieldWidth, heightCellNum * fieldHeight);

        // draw all fields
        gc.setFill(Color.BLACK);
        for (int i = 0; i < widthCellNum; i++) {
            for (int j = 0; j < heightCellNum; j++) {
                gc.fillRoundRect(i*fieldWidth, j*fieldHeight, fieldWidth,fieldHeight, 5, 5);
            }
        }

        // draw walls
        for (Walls walls : walls) {
            gc.setFill(walls.getColor());
            gc.fillRoundRect(walls.getY() * fieldWidth, walls.getX() * fieldHeight, fieldWidth, fieldHeight, 5, 5);
        }


        // draw 'ghostOne'
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(this.ghostOne.getX() * fieldWidth, this.ghostOne.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        // draw 'ghostOne'
        gc.setFill(Color.PINK);
        gc.fillRoundRect(this.ghostTwo.getX() * fieldWidth, this.ghostTwo.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        // draw 'ghostOne'
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRoundRect(this.ghostThree.getX() * fieldWidth, this.ghostThree.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        // draw 'pacMan'
        gc.setFill(Color.YELLOW);
        gc.fillRoundRect(this.pacMan.getX() * fieldWidth, this.pacMan.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

    }

    //---------------------------------- BUTTONS -----------------------------------
    /**
     * Start game
     * Start button method, executed when button is pressed
     */
    public void btnStartAction(ActionEvent event)
    {
        label1.setText("Started");
        System.out.println("Start btn pressed");
        gameTimer();
    }

    /**
     * Close window
     * Close button method, closes fxml end ends program
     */
    public void btnCloseAction(ActionEvent event)
    {
        label1.setText("Closing...");
        System.out.println("Close btn pressed");
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
