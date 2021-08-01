package app.GlobalSignalMaps;

import javafx.animation.Timeline;
import java.util.HashMap;
import java.util.Map;

public enum animationMap {
    INSTANCE;

    public Map<Integer, Timeline> timelines = new HashMap<>();
}
