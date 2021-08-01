package app.Controllers;

import app.ThreadManager.RunnableScheduler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import app.GlobalSignalMaps.widgetMap;
import app.SocialMediaParsers.youtubeParser;
import app.SocialMediaParsers.redditParser;
import app.SocialMediaParsers.twitterParser;
import app.GlobalSignalMaps.redditMaps;
import app.GlobalSignalMaps.youtubeMaps;
import javafx.scene.paint.Color;

public class bigdataFeedScreen implements Initializable {

    @FXML
    private Pane bigdatafeedScreen;
    @FXML
    private JFXListView socialFeed;
    @FXML
    private Label socialFeedTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        widgetMap.INSTANCE.socialFeed.put(0,socialFeed);
        widgetMap.INSTANCE.socialFeedTitle.put(0, socialFeedTitle);

        startSocialFeedCollection();

    }
    public void startSocialFeedCollection(){
        Runnable updateRedditData = () -> {
            redditParser.INSTANCE.getRedditData();
            youtubeParser.INSTANCE.getYoutubeData();
            twitterParser.INSTANCE.getTwitterData();
        };
        RunnableScheduler.INSTANCE.execService.scheduleAtFixedRate(updateRedditData,0, 5, TimeUnit.MINUTES);
    }
    public void showyoutubeFeed(MouseEvent event) throws IOException {
        if(widgetMap.INSTANCE.socialFeed.get(0).getItems().size()>0) {
            widgetMap.INSTANCE.socialFeed.get(0).getItems().clear();
        }
        for(int a = 0; a <= redditMaps.INSTANCE.newPosts.size(); a++) {
            Pane postPane = new Pane();
            postPane.setStyle("-fx-background-color: black;");
            postPane.setPrefHeight(40);
            postPane.setPrefWidth(535);

            Label postTitle = new Label();
            postTitle.setText(youtubeMaps.INSTANCE.newPosts.get(a));
            postTitle.setTextFill(Color.WHITE);
            postTitle.setPrefHeight(40);
            postTitle.setPrefWidth(535);

            postPane.getChildren().add(postTitle);

            widgetMap.INSTANCE.socialFeed.get(0).getItems().add(postPane);
            widgetMap.INSTANCE.socialFeedTitle.get(0).setText("Trending Youtube Posts");
        }
    }
    public void showredditFeed(MouseEvent event) throws IOException {
        if(widgetMap.INSTANCE.socialFeed.get(0).getItems().size()>0) {
            widgetMap.INSTANCE.socialFeed.get(0).getItems().clear();
        }
        for(int a = 0; a <= redditMaps.INSTANCE.newPosts.size(); a++) {
            Pane postPane = new Pane();
            postPane.setStyle("-fx-background-color: black;");
            postPane.setPrefHeight(40);
            postPane.setPrefWidth(535);

            Label postTitle = new Label();
            postTitle.setText(redditMaps.INSTANCE.newPosts.get(a));
            postTitle.setTextFill(Color.WHITE);
            postTitle.setPrefHeight(40);
            postTitle.setPrefWidth(535);

            postPane.getChildren().add(postTitle);

            widgetMap.INSTANCE.socialFeed.get(0).getItems().add(postPane);
            widgetMap.INSTANCE.socialFeedTitle.get(0).setText("Trending Reddit Posts");
        }
    }
    public void showtwitterFeed(MouseEvent event) throws IOException {
        if(widgetMap.INSTANCE.socialFeed.get(0).getItems().size()>0) {
            widgetMap.INSTANCE.socialFeed.get(0).getItems().clear();
        }
        for(int a = 0; a <= redditMaps.INSTANCE.newPosts.size(); a++) {
            Pane postPane = new Pane();
            postPane.setStyle("-fx-background-color: black;");
            postPane.setPrefHeight(40);
            postPane.setPrefWidth(535);

            Label postTitle = new Label();
            postTitle.setText(redditMaps.INSTANCE.newPosts.get(a));
            postTitle.setTextFill(Color.WHITE);
            postTitle.setPrefHeight(40);
            postTitle.setPrefWidth(535);

            postPane.getChildren().add(postTitle);

            widgetMap.INSTANCE.socialFeed.get(0).getItems().add(postPane);
            widgetMap.INSTANCE.socialFeedTitle.get(0).setText("Trending Reddit Posts");
        }
    }
}
