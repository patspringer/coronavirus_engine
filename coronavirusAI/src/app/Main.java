package app;

import app.Controllers.mainScreen;
import app.ThreadManager.RunnableScheduler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static Map<Integer, Pane> sceneMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/mainScreen.fxml"));

        Parent root = loader.load();
        mainScreen controller = loader.getController();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        controller.focus();

        primaryStage.show();

        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        Runnable addSceneMap = () -> {
            try {
                Pane dataEngine = FXMLLoader.load(getClass().getResource("/app/views/dataEngineScreen.fxml"));
                Pane caseTracker = FXMLLoader.load(getClass().getResource("/app/views/caseTrackerScreen.fxml"));
                Pane liveheatMap = FXMLLoader.load(getClass().getResource("/app/views/heatMapScreen.fxml"));
                Pane bigdataFeed = FXMLLoader.load(getClass().getResource("/app/views/bigdataFeedScreen.fxml"));
                Pane simulationViewer = FXMLLoader.load(getClass().getResource("/app/views/simulationViewerScreen.fxml"));

                sceneMap.put(0, dataEngine);
                sceneMap.put(1, caseTracker);
                sceneMap.put(2, liveheatMap);
                sceneMap.put(3, bigdataFeed);
                sceneMap.put(4, simulationViewer);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        };
        RunnableScheduler.INSTANCE.executor.submit(addSceneMap);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
