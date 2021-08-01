package app.Controllers;

import app.GlobalSignalMaps.widgetMap;
import app.LiveCaseParsers.heatmapParser;
import app.ThreadManager.RunnableScheduler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class heatMapScreen implements Initializable {

    @FXML
    private Pane heatmapScreen;
    @FXML
    private Label heatmapTitle;
    @FXML
    private Label newYork;
    @FXML
    private Label newJersey;
    @FXML
    private Label michigan;
    @FXML
    private Label california;
    @FXML
    private Label louisiana;
    @FXML
    private Label florida;
    @FXML
    private Label massachusetts;
    @FXML
    private Label pennsylvania;
    @FXML
    private Label illinois;
    @FXML
    private Label washington;
    @FXML
    private Label georgia;
    @FXML
    private Label texas;
    @FXML
    private Label connecticut;
    @FXML
    private Label colorado;
    @FXML
    private Label indiana;
    @FXML
    private Label ohio;
    @FXML
    private Label tennessee;
    @FXML
    private Label maryland;
    @FXML
    private Label northCarolina;
    @FXML
    private Label virginia;
    @FXML
    private Label missouri;
    @FXML
    private Label arizona;
    @FXML
    private Label wisconsin;
    @FXML
    private Label southCarolina;
    @FXML
    private Label nevada;
    @FXML
    private Label mississippi;
    @FXML
    private Label alabama;
    @FXML
    private Label utah;
    @FXML
    private Label oklahoma;
    @FXML
    private Label idaho;
    @FXML
    private Label oregon;
    @FXML
    private Label minnesota;
    @FXML
    private Label kentucky;
    @FXML
    private Label columbia;
    @FXML
    private Label iowa;
    @FXML
    private Label arkansas;
    @FXML
    private Label rhodeIsland;
    @FXML
    private Label kansas;
    @FXML
    private Label newHampshire;
    @FXML
    private Label maine;
    @FXML
    private Label newMexico;
    @FXML
    private Label delaware;
    @FXML
    private Label vermont;
    @FXML
    private Label hawaii;
    @FXML
    private Label nebraska;
    @FXML
    private Label montana;
    @FXML
    private Label westVirginia;
    @FXML
    private Label southDakota;
    @FXML
    private Label northDakota;
    @FXML
    private Label wyoming;
    @FXML
    private Label alaska;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        widgetMap.INSTANCE.heatmapTitle.put(0, heatmapTitle);
        widgetMap.INSTANCE.stateData.put(0, newYork);
        widgetMap.INSTANCE.stateData.put(1, newJersey);
        widgetMap.INSTANCE.stateData.put(2, michigan);
        widgetMap.INSTANCE.stateData.put(3, california);
        widgetMap.INSTANCE.stateData.put(4, louisiana);
        widgetMap.INSTANCE.stateData.put(5, florida);
        widgetMap.INSTANCE.stateData.put(6, massachusetts);
        widgetMap.INSTANCE.stateData.put(7, pennsylvania);
        widgetMap.INSTANCE.stateData.put(8, illinois);
        widgetMap.INSTANCE.stateData.put(9, washington);
        widgetMap.INSTANCE.stateData.put(10, georgia);
        widgetMap.INSTANCE.stateData.put(11, texas);
        widgetMap.INSTANCE.stateData.put(12, connecticut);
        widgetMap.INSTANCE.stateData.put(13, colorado);
        widgetMap.INSTANCE.stateData.put(14, indiana);
        widgetMap.INSTANCE.stateData.put(15, ohio);
        widgetMap.INSTANCE.stateData.put(16, tennessee);
        widgetMap.INSTANCE.stateData.put(17, maryland);
        widgetMap.INSTANCE.stateData.put(18, northCarolina);
        widgetMap.INSTANCE.stateData.put(19, virginia);
        widgetMap.INSTANCE.stateData.put(20, missouri);
        widgetMap.INSTANCE.stateData.put(21, arizona);
        widgetMap.INSTANCE.stateData.put(22, wisconsin);
        widgetMap.INSTANCE.stateData.put(23, southCarolina);
        widgetMap.INSTANCE.stateData.put(24, nevada);
        widgetMap.INSTANCE.stateData.put(25, mississippi);
        widgetMap.INSTANCE.stateData.put(26, alabama);
        widgetMap.INSTANCE.stateData.put(27, utah);
        widgetMap.INSTANCE.stateData.put(28, oklahoma);
        widgetMap.INSTANCE.stateData.put(29, idaho);
        widgetMap.INSTANCE.stateData.put(30, oregon);
        widgetMap.INSTANCE.stateData.put(31, minnesota);
        widgetMap.INSTANCE.stateData.put(32, kentucky);
        widgetMap.INSTANCE.stateData.put(33, columbia);
        widgetMap.INSTANCE.stateData.put(34, iowa);
        widgetMap.INSTANCE.stateData.put(35, arkansas);
        widgetMap.INSTANCE.stateData.put(36, rhodeIsland);
        widgetMap.INSTANCE.stateData.put(37, kansas);
        widgetMap.INSTANCE.stateData.put(38, newHampshire);
        widgetMap.INSTANCE.stateData.put(39, maine);
        widgetMap.INSTANCE.stateData.put(40, newMexico);
        widgetMap.INSTANCE.stateData.put(41, delaware);
        widgetMap.INSTANCE.stateData.put(42, vermont);
        widgetMap.INSTANCE.stateData.put(43, hawaii);
        widgetMap.INSTANCE.stateData.put(44, nebraska);
        widgetMap.INSTANCE.stateData.put(45, montana);
        widgetMap.INSTANCE.stateData.put(46, westVirginia);
        widgetMap.INSTANCE.stateData.put(47, southDakota);
        widgetMap.INSTANCE.stateData.put(48, northDakota);
        widgetMap.INSTANCE.stateData.put(49, wyoming);
        widgetMap.INSTANCE.stateData.put(50, alaska);

        startheatmapCollection();
    }

    public void startheatmapCollection(){
        Runnable updateheatmapData = () -> {
            heatmapParser.INSTANCE.getLiveData();
        };
        RunnableScheduler.INSTANCE.execService.scheduleAtFixedRate(updateheatmapData,0, 5, TimeUnit.MINUTES);
    }
}
