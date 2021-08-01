package app.Controllers;

import app.GlobalSignalMaps.animationMap;
import app.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import java.net.URL;
import java.util.ResourceBundle;

public class simulationViewerScreen implements Initializable {

    @FXML
    private Pane simulationViewerScreen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static void reloadMathematics() {
        FontIcon circleLoad1 = new FontIcon();
        circleLoad1.setIconSize(30);
        circleLoad1.setLayoutX(220);
        circleLoad1.setLayoutY(220);
        circleLoad1.setIconLiteral("fa-circle");
        circleLoad1.setIconColor(Color.valueOf("#54595f"));

        FontIcon circleLoad2 = new FontIcon();
        circleLoad2.setIconSize(30);
        circleLoad2.setLayoutX(320);
        circleLoad2.setLayoutY(220);
        circleLoad2.setIconLiteral("fa-circle");
        circleLoad2.setIconColor(Color.valueOf("#54595f"));

        FontIcon circleLoad3 = new FontIcon();
        circleLoad3.setIconSize(30);
        circleLoad3.setLayoutX(420);
        circleLoad3.setLayoutY(220);
        circleLoad3.setIconLiteral("fa-circle");
        circleLoad3.setIconColor(Color.valueOf("#54595f"));

        FontIcon circleLoad4 = new FontIcon();
        circleLoad4.setIconSize(30);
        circleLoad4.setLayoutX(520);
        circleLoad4.setLayoutY(220);
        circleLoad4.setIconLiteral("fa-circle");
        circleLoad4.setIconColor(Color.valueOf("#54595f"));

        Main.sceneMap.get(4).getChildren().add(circleLoad1);
        Main.sceneMap.get(4).getChildren().add(circleLoad2);
        Main.sceneMap.get(4).getChildren().add(circleLoad3);
        Main.sceneMap.get(4).getChildren().add(circleLoad4);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> circleLoad1.setIconColor(Color.valueOf("#3fea5e"))),
                new KeyFrame(Duration.seconds(1.0), e -> circleLoad1.setIconColor(Color.valueOf("#54595f"))),
                new KeyFrame(Duration.seconds(1.5), e -> circleLoad2.setIconColor(Color.valueOf("#3fea5e"))),
                new KeyFrame(Duration.seconds(2.0), e -> circleLoad2.setIconColor(Color.valueOf("#54595f"))),
                new KeyFrame(Duration.seconds(2.5), e -> circleLoad3.setIconColor(Color.valueOf("#3fea5e"))),
                new KeyFrame(Duration.seconds(3.0), e -> circleLoad3.setIconColor(Color.valueOf("#54595f"))),
                new KeyFrame(Duration.seconds(3.5), e -> circleLoad4.setIconColor(Color.valueOf("#3fea5e"))),
                new KeyFrame(Duration.seconds(4.0), e -> circleLoad4.setIconColor(Color.valueOf("#54595f")))
        );

        timeline.setCycleCount(Animation.INDEFINITE);

        animationMap.INSTANCE.timelines.put(0, timeline);

        animationMap.INSTANCE.timelines.get(0).play();

    }
}
