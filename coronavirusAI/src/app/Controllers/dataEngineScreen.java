package app.Controllers;

import app.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import app.GlobalSignalMaps.animationMap;

public class dataEngineScreen implements Initializable {

    @FXML
    private StackPane dataengineScreen;
    @FXML
    private Pane viewChanger;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void gotocaseTracker(MouseEvent event) throws IOException {
        viewChanger.getChildren().setAll(Main.sceneMap.get(1));
    }
    public void gotoliveHeatmap(MouseEvent event) throws IOException {
        viewChanger.getChildren().setAll(Main.sceneMap.get(2));
    }
    public void gotobigdataFeed(MouseEvent event) throws IOException {
        viewChanger.getChildren().setAll(Main.sceneMap.get(3));
    }
    public void gotosimulationView(MouseEvent event) throws IOException {
        if (animationMap.INSTANCE.timelines.get(0)!=null){
            animationMap.INSTANCE.timelines.get(0).stop();
            animationMap.INSTANCE.timelines.remove(0);
        }
        simulationViewerScreen.reloadMathematics();
        viewChanger.getChildren().setAll(Main.sceneMap.get(4));
    }
    public void closeApplication(MouseEvent event) throws IOException {
        Platform.exit();
        System.exit(0);

    }
}
