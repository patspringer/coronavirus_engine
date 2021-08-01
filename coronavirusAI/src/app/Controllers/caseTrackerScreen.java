package app.Controllers;

import app.GlobalSignalMaps.livecaseMaps;
import app.GlobalSignalMaps.widgetMap;
import app.LiveCaseParsers.overallcasesParser;
import app.ThreadManager.RunnableScheduler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class caseTrackerScreen implements Initializable {

    @FXML
    private Pane casetrackerScreen;
    @FXML
    private Label totalCases;
    @FXML
    private Label totalNewCases;
    @FXML
    private Label totalDeaths;
    @FXML
    private Label totalcasesText;
    @FXML
    private Label newcasesText;
    @FXML
    private Label totaldeathsText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        widgetMap.INSTANCE.overallData.put(0, totalCases);
        widgetMap.INSTANCE.overallData.put(1, totalNewCases);
        widgetMap.INSTANCE.overallData.put(2, totalDeaths);
        widgetMap.INSTANCE.overallData.put(3, totalcasesText);
        widgetMap.INSTANCE.overallData.put(4, newcasesText);
        widgetMap.INSTANCE.overallData.put(5, totaldeathsText);

        startcaseTrackerCollection();
    }

    public void startcaseTrackerCollection(){
        Runnable updatecaseData = () -> {
            overallcasesParser.INSTANCE.getLiveData();
        };
        RunnableScheduler.INSTANCE.execService.scheduleAtFixedRate(updatecaseData,0, 5, TimeUnit.MINUTES);
    }

    public void showcasesToday(MouseEvent event) throws IOException {
        widgetMap.INSTANCE.overallData.get(0).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(0));
        widgetMap.INSTANCE.overallData.get(1).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(1));
        widgetMap.INSTANCE.overallData.get(2).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(2));
        widgetMap.INSTANCE.overallData.get(3).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(4).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(5).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(3).setText("Total Cases");
        widgetMap.INSTANCE.overallData.get(4).setText("Total New Cases");
        widgetMap.INSTANCE.overallData.get(5).setText("Total Deaths");
    }
    public void showcasesYesterday(MouseEvent event) throws IOException {
        widgetMap.INSTANCE.overallData.get(0).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(3));
        widgetMap.INSTANCE.overallData.get(1).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(4));
        widgetMap.INSTANCE.overallData.get(2).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(5));
        widgetMap.INSTANCE.overallData.get(3).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(4).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(5).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 35");
        widgetMap.INSTANCE.overallData.get(3).setText("Total Cases");
        widgetMap.INSTANCE.overallData.get(4).setText("Total New Cases");
        widgetMap.INSTANCE.overallData.get(5).setText("Total Deaths");
    }
    public void showpercentIncrease(MouseEvent event) throws IOException {
        DecimalFormat df = new DecimalFormat("#.##");
        double totalCasePercentIncrease = Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(3))
                / Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(0));
        totalCasePercentIncrease = (1 - totalCasePercentIncrease) * 100;
        String cleantotalCasePercentDecimals = df.format(totalCasePercentIncrease);

        double newCasePercentIncrease = Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(4))
                / Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(1));
        newCasePercentIncrease = (1 - newCasePercentIncrease) * 100;
        String cleannewCasePercentDecimals = df.format(newCasePercentIncrease);

        double deathPercentIncrease = Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(5))
                / Double.valueOf(livecaseMaps.INSTANCE.overallCaseDoubles.get(2));
        deathPercentIncrease = (1 - deathPercentIncrease) * 100;
        String cleandeathPercentDecimals = df.format(deathPercentIncrease);

        widgetMap.INSTANCE.overallData.get(0).setText(cleantotalCasePercentDecimals + "%");
        widgetMap.INSTANCE.overallData.get(1).setText(cleannewCasePercentDecimals + "%");
        widgetMap.INSTANCE.overallData.get(2).setText(cleandeathPercentDecimals + "%");
        widgetMap.INSTANCE.overallData.get(3).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 25");
        widgetMap.INSTANCE.overallData.get(4).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 25");
        widgetMap.INSTANCE.overallData.get(5).setStyle("-fx-font-family: Haettenschweiler; -fx-font-size: 25");
        widgetMap.INSTANCE.overallData.get(3).setText("Total Cases % Increase");
        widgetMap.INSTANCE.overallData.get(4).setText("New Cases % Increase");
        widgetMap.INSTANCE.overallData.get(5).setText("Total Deaths % Increase");
    }
}
