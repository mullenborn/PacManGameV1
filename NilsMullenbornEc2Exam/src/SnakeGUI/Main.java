/**
 * Main.java
 * @author Nils Müllenborn, nimu@ruc.dk
 * Created on 28 March 2019
 */
package SnakeGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PACMAN Recreated by Nils Müllenborn");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
        primaryStage.getScene().getStylesheets().add("SnakeGUI/fancy.css");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
