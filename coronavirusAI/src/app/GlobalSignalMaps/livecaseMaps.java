package app.GlobalSignalMaps;

import java.util.HashMap;
import java.util.Map;

public enum livecaseMaps {
    INSTANCE;

    public Map<Integer, String> overallCaseStrings = new HashMap<>();
    public Map<Integer, Double> overallCaseDoubles = new HashMap<>();
    public Map<Integer, String> heatmapTodayStrings = new HashMap<>();
    public Map<Integer, String> heatmapYesterdayStrings = new HashMap<>();
    public Map<Integer, Double> heatmapTodayDoubles = new HashMap<>();
    public Map<Integer, Double> heatmapYesterdayDoubles = new HashMap<>();
}
