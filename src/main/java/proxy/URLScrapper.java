package proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class URLScrapper implements Scrapper {

    @Override
    public String parseHtmlByUrl(String urlString, String modifier) throws IOException {
        String html = "";
        String query = URLEncoder.encode(modifier, StandardCharsets.UTF_8);
        query = urlString + query;
        URL url = new URL(query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Insert your configs");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            html = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
            // print result
            //System.out.println(html);
        } else {
            System.out.println("GET request did not work.");
            html = "ERROR OCURRED";
        }
        // connection.connect();
        return html;
    }
}
