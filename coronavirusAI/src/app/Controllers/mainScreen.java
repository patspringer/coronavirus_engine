package app.Controllers;

import app.Main;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class mainScreen implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private StackPane mainScreen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void gotoliveData(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(Main.sceneMap.get(0)));
        Main.sceneMap.get(0).requestFocus();

        //grab your root here
        Main.sceneMap.get(0).setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        Main.sceneMap.get(0).setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public void closeApplication(MouseEvent event) throws IOException {
        Platform.exit();
        System.exit(0);

    }
    public void focus() {
        mainScreen.requestFocus();
    }
}
