package app.SocialMediaParsers;

import app.GlobalSignalMaps.youtubeMaps;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public enum youtubeParser {
    INSTANCE;

    int x = 0;

    public void getYoutubeData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.youtube.com/results?search_query=%23coronavirus&sp=EgIIAw%253D%253D"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String bodyResponse = response.body();

            if (response.statusCode() == 200) {
                int heatmapToday = bodyResponse.indexOf("yt-uix-tile-link");
                String substring1 = bodyResponse.substring(heatmapToday + 10);
                String split1 = substring1.split("branded-page-box")[0];


                for (int i = -1; (i = split1.indexOf("title=", i + 1)) != -1; i++) {
                    String substring = split1.substring(i + 7);

                    String split = substring.split("\"")[0];

                    if(!split.contains("Verified") && !split.contains("Watch")
                            && !split.contains("https")) {
                        youtubeMaps.INSTANCE.newPosts.put(x, split);
                        x++;
                    }
                }

            } else {
                System.out.println("Error Trying to get live corona virus data");
            }
        }
        catch (Exception e){
            System.out.println("Live Cases Parser Error: " + e);
        }
    }
}
