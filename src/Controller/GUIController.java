package Controller;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class GUIController extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/GUIA8.fxml"));

        Scene scene = new Scene(root, 1000, 800);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public static  void main(String[] args){
        launch(args);
    }
}
