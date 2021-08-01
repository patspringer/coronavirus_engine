package app.LiveCaseParsers;

import app.GlobalSignalMaps.livecaseMaps;
import app.GlobalSignalMaps.widgetMap;
import app.ThreadManager.RunnableScheduler;
import javafx.application.Platform;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public enum heatmapParser {
    INSTANCE;

    int x = 0;
    int y = 0;
    int flashState = 0;

    String[] states = {"New York", "New Jersey", "Michigan", "California", "Louisiana", "Florida",
                        "Massachusetts", "Pennsylvania", "Illinois", "Washington", "Georgia", "Texas",
                        "Connecticut", "Colorado", "Indiana", "Ohio", "Tennessee", "Maryland", "North Carolina",
                        "Virginia", "Missouri", "Arizona", "Wisconsin", "South Carolina", "Nevada", "Mississippi",
                        "Alabama", "Utah", "Oklahoma", "Idaho", "Oregon", "Minnesota", "Kentucky", "Columbia",
                        "Columbia", "Iowa", "Arkansas", "Rhode Island", "Kansas", "New Hampshire", "Maine",
                        "New Mexico", "Delaware", "Vermont", "Hawaii", "Nebraska", "Montana", "West Virginia",
                        "South Dakota", "North Dakota", "Wyoming", "Alaska"};

    public void getLiveData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.worldometers.info/coronavirus/country/us/"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String bodyResponse = response.body();

            if (response.statusCode() == 200) {
                //Parse Today's Total Cases
                int heatmapToday = bodyResponse.indexOf("table id=\"usa_table_countries_today\"");
                String substringToday1 = bodyResponse.substring(heatmapToday + 90);
                String splitToday1 = substringToday1.split("table id=\"usa_table_countries_yesterday\"")[0];

                for (int i = -1; (i = splitToday1.indexOf("<tr style=\"\">", i + 1)) != -1; i++) {
                    String substring = splitToday1.substring(i + 70);

                    String split = substring.split("</td>")[1];
                    int finalString = split.indexOf("text-align:right");
                    String finalSplit = split.substring(finalString + 18);

                    String convertoDouble = "";
                    Double value = 0.0;
                    if(finalSplit.contains(",")) {
                        convertoDouble = finalSplit.replace(",", "");
                        value = Double.valueOf(convertoDouble);
                    }
                    livecaseMaps.INSTANCE.heatmapTodayStrings.put(x, finalSplit);
                    livecaseMaps.INSTANCE.heatmapTodayDoubles.put(x, value);
                    x++;
                }
                //Parse Yesterday's Total Cases
                int heatmapYesterday = bodyResponse.indexOf("table id=\"usa_table_countries_yesterday\"");
                String substringYesterday1 = bodyResponse.substring(heatmapYesterday + 90);
                String splitYesterday1 = substringYesterday1.split("table id=\"usa_table_countries_yesterday\"")[0];

                for (int i = -1; (i = splitYesterday1.indexOf("<tr style=\"\">", i + 1)) != -1; i++) {
                    String substring = splitYesterday1.substring(i + 70);

                    String split = substring.split("</td>")[1];

                    int finalString = split.indexOf("text-align:right");
                    String finalSplit = split.substring(finalString + 18);

                    String convertoDouble = "";
                    Double value = 0.0;
                    if(finalSplit.contains(",")) {
                        convertoDouble = finalSplit.replace(",", "");
                        value = Double.valueOf(convertoDouble);
                    }

                    livecaseMaps.INSTANCE.heatmapYesterdayStrings.put(y, finalSplit);
                    livecaseMaps.INSTANCE.heatmapYesterdayDoubles.put(y, value);
                    y++;
                }

            } else {
                System.out.println("Error Trying to get live coronavirus state data");
            }
        }
        catch (Exception e){
            System.out.println("Live Cases Parser Error: " + e);
        }

        Runnable updateheatmapData = () -> {
            Platform.runLater(() -> {
                for (int b = 0; b <= livecaseMaps.INSTANCE.heatmapTodayStrings.size(); b++) {
                    if (widgetMap.INSTANCE.stateData.get(b) != null) {
                        if (flashState == 0) {
                            widgetMap.INSTANCE.heatmapTitle.get(0).setText("States Listed");
                            widgetMap.INSTANCE.stateData.get(b).setText(states[b]);
                        }
                        else if (flashState == 1){
                            widgetMap.INSTANCE.stateData.get(b).setText(livecaseMaps.INSTANCE.heatmapTodayStrings.get(b));
                            widgetMap.INSTANCE.heatmapTitle.get(0).setText("Today's Total Cases");
                        }
                        else if (flashState == 2){
                            widgetMap.INSTANCE.stateData.get(b).setText(livecaseMaps.INSTANCE.heatmapYesterdayStrings.get(b));
                            widgetMap.INSTANCE.heatmapTitle.get(0).setText("Yesterday's Total Cases");
                        }
                        else if (flashState == 3){
                            for(int c = 0; c <= livecaseMaps.INSTANCE.heatmapYesterdayDoubles.size(); c++) {
                                if(widgetMap.INSTANCE.stateData.get(c)!= null) {
                                    DecimalFormat df = new DecimalFormat("#.###");
                                    double PercentIncrease = Double.valueOf(livecaseMaps.INSTANCE.heatmapYesterdayDoubles.get(c))
                                            / Double.valueOf(livecaseMaps.INSTANCE.heatmapTodayDoubles.get(c));
                                    PercentIncrease = (1 - PercentIncrease) * 100;

                                    String cleanPercentDecimals = df.format(PercentIncrease);
                                    widgetMap.INSTANCE.stateData.get(c).setText(cleanPercentDecimals + "%");
                                }
                            }
                            widgetMap.INSTANCE.heatmapTitle.get(0).setText("% Increase in Cases");
                        }
                    }
                }

                if(flashState != 3) {
                    flashState = flashState + 1;
                }
                else {
                    flashState = 0;
                }
            });
        };
        RunnableScheduler.INSTANCE.execService.scheduleAtFixedRate(updateheatmapData,0, 5, TimeUnit.SECONDS);
    }
}
