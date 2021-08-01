package app.LiveCaseParsers;

import app.GlobalSignalMaps.livecaseMaps;
import app.GlobalSignalMaps.widgetMap;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public enum overallcasesParser {
    INSTANCE;

    int x = 0;

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
                //Parse Today's Case Data
                int overallCasesToday = bodyResponse.indexOf("table id=\"usa_table_countries_today\"");
                String substring1 = bodyResponse.substring(overallCasesToday + 40);
                int total_row_usa = substring1.indexOf("total_row_usa");
                String substring2 = substring1.substring(total_row_usa);
                String split1 = substring2.split("</tr>")[0];

                for (int i = -1; (i = split1.indexOf("<td>", i + 1)) != -1; i++) {
                    String tableData = split1.substring(i + 4);

                    String splitData = tableData.split("</td>")[0];
                    if (x <= 2) {
                        String convertoDouble = "";
                        Double value = 0.0;
                        if(splitData.contains(",")) {
                            convertoDouble = splitData.replace(",", "");
                            value = Double.valueOf(convertoDouble);
                        }
                        else if(splitData.contains("+")){
                            convertoDouble = splitData.replace("+","");
                            value = Double.valueOf(convertoDouble);
                        }
                        else if (splitData.contains(",") && splitData.contains("+")){
                            convertoDouble = splitData.replace(",", "");
                            String cleanDouble = convertoDouble.replace("+","");
                            value = Double.valueOf(cleanDouble);
                        }
                        livecaseMaps.INSTANCE.overallCaseStrings.put(x, splitData);
                        livecaseMaps.INSTANCE.overallCaseDoubles.put(x, value);
                        x++;
                    }
                }

                //Parse Yesterday's Case Data
                int overallCasesYesterday = bodyResponse.indexOf("table id=\"usa_table_countries_yesterday\"");
                String substringYesterday1 = bodyResponse.substring(overallCasesYesterday + 40);
                int total_row_usa_yesterday = substringYesterday1.indexOf("total_row_usa");
                String substringYesterday2 = substringYesterday1.substring(total_row_usa_yesterday);
                String splitYesterday = substringYesterday2.split("</tr>")[0];

                for (int i = -1; (i = splitYesterday.indexOf("<td>", i + 1)) != -1; i++) {
                    String tableData = splitYesterday.substring(i + 4);

                    String splitData = tableData.split("</td>")[0];

                    if(x<=5) {
                        String convertoDouble = "";
                        Double value = 0.0;
                        if (splitData.contains(",")) {
                            convertoDouble = splitData.replace(",", "");
                            value = Double.valueOf(convertoDouble);
                        }
                        else if (splitData.contains("+")) {
                            convertoDouble = splitData.replace("+", "");
                            value = Double.valueOf(convertoDouble);
                        }
                        else if (splitData.contains(",") && splitData.contains("+")){
                            convertoDouble = splitData.replace(",", "");
                            String cleanDouble = convertoDouble.replace("+","");
                            value = Double.valueOf(cleanDouble);
                        }
                        livecaseMaps.INSTANCE.overallCaseStrings.put(x, splitData);
                        livecaseMaps.INSTANCE.overallCaseDoubles.put(x, value);
                        x++;
                    }
                }

                widgetMap.INSTANCE.overallData.get(0).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(0));
                widgetMap.INSTANCE.overallData.get(1).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(1));
                widgetMap.INSTANCE.overallData.get(2).setText(livecaseMaps.INSTANCE.overallCaseStrings.get(2));

            } else {
                System.out.println("Error Trying to get live coronavirus state data");
            }
        }
        catch (Exception e){
            System.out.println("Live Cases Parser Error: " + e);
        }
    }
}
