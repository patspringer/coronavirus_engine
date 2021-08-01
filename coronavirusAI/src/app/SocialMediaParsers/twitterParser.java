package app.SocialMediaParsers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import app.GlobalSignalMaps.twitterMaps;

public enum twitterParser {
    INSTANCE;

    int x = 0;

    public void getTwitterData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://twitter.com/search?q=coronavirus"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String bodyResponse = response.body();

            if (response.statusCode() == 200) {
                for (int i = -1; (i = bodyResponse.indexOf("js-tweet-text-container", i + 1)) != -1; i++) {

                    String substring = bodyResponse.substring(i + 114);
                    String finalSubstring = substring.split("</div>")[0];

                    if (finalSubstring.contains("<strong>") || finalSubstring.contains("<a>")
                            || finalSubstring.contains("</p>") || finalSubstring.contains("rel=\"nofollow noopener\"")){
                        String removeExtraLanguage1 = finalSubstring.replaceAll("<strong>","");
                        String removeExtraLanguage2 = removeExtraLanguage1.replaceAll("</strong>","");
                        String removeExtraLanguage3 = removeExtraLanguage2.replaceAll("<a>","");
                        String removeExtraLanguage4 = removeExtraLanguage3.replaceAll("</a>","");
                        String removeExtraLanguage5 = removeExtraLanguage4.replaceAll("</p>","");
                        String removeExtraLanguage6 = removeExtraLanguage5.replaceAll("</span>","");
                        String removeExtraLanguage7 = removeExtraLanguage6.replaceAll("rel=\"nofollow noopener\"","");
                        String removeExtraLanguage8 = removeExtraLanguage7.replaceAll("<a href=.*\"","");
                        String removeExtraLanguage9 = removeExtraLanguage8.replaceAll("src=.*\"","");
                        String removeExtraLanguage10 = removeExtraLanguage9.replaceAll(">pic.*","");
                        String removeExtraLanguage11 = removeExtraLanguage10.replaceAll("&quot;","");
                        String removeExtraLanguage12 = removeExtraLanguage11.replaceAll(">&nbsp;","");
                        String removeExtraLanguage13 = removeExtraLanguage12.replaceAll("&#39;","'");
                        String removeWhiteSpace = removeExtraLanguage13.replaceAll("\n","");

                        twitterMaps.INSTANCE.newPosts.put(x, removeWhiteSpace);
                        x++;

                    }
                    else{
                        System.out.println("Twitter Parser Error");
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Twitter Parser Error: " + e);
        }
    }
}
