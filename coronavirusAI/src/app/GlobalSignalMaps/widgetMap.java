package app.GlobalSignalMaps;

import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import java.util.HashMap;
import java.util.Map;

public enum widgetMap {
    INSTANCE;

    public Map<Integer, Label> overallData = new HashMap<>();
    public Map<Integer, Label> stateData = new HashMap<>();
    public Map<Integer, JFXListView> socialFeed = new HashMap<>();
    public Map<Integer, Label> socialFeedTitle = new HashMap<>();
    public Map<Integer, Label> heatmapTitle = new HashMap<>();
}
