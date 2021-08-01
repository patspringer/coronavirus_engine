package app.SocialMediaParsers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import app.GlobalSignalMaps.redditMaps;

public enum redditParser {
    INSTANCE;

    int x = 0;

    public void getRedditData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.reddit.com/r/coronavirus/new/.json?t=minute&limit=10"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String bodyResponse = response.body();

            for (int i = -1; (i = bodyResponse.indexOf("\"title\":", i + 1)) != -1; i++) {
                String titlename = response.body().substring(i + 10);
                String finalName = titlename.split("\",")[0];

                redditMaps.INSTANCE.newPosts.put(x, finalName);
                x++;
            }
        }
        catch (Exception e){
            System.out.println("Reddit Parser Error: " + e);
        }
    }
}
