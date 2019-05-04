package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.*;

public class kopak extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUIA8.fxml"));

        Scene scene = new Scene(root, 800, 675);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }
    public static  void main(String[] args){
        launch(args);
    }
}