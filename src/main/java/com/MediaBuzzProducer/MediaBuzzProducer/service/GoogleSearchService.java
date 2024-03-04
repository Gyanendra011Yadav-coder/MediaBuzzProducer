package com.MediaBuzzProducer.MediaBuzzProducer.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GoogleSearchService {

    private static final String API_KEY = "AIzaSyB8Wz7thAr5v8xNiAPB5uU8N5d2wa_1Xdg";
    private static final String CX_ID = "723d493547ffb4712";
    private static String publicationsQuery = "site:barandbench.com OR site:livelaw.in OR site:lexology.com OR site:indiatoday.in OR site:economictimes.indiatimes.com OR site:thehindubusinessline.com OR site:bombayhighcourt.nic.in OR site:calcuttahighcourt.nic.in OR site:madraslawjournal.com OR site:maharashtralawjournal.com OR site:thelawyermagazine.in OR site:spicyip.com OR site:ipleaders.in OR site:altlawforum.org OR site:lawctopus.com OR site:nlsiu.ac.in OR site:livemint.com OR site:thehindu.com OR site:timesofindia.indiatimes.com OR site:hindustantimes.com OR site:indianexpress.com OR site:deccanherald.com OR site:telegraphindia.com OR site:legallyindia.com OR site:theleaflet.in OR site:manupatrafast.com OR site:ndtv.com OR site:theprint.in OR site:scroll.in OR site:thequint.com OR site:outlookindia.com";
    private static final String SEARCH_QUERY = "lakshmikumaran & sridharan"+ publicationsQuery; //private static final String SEARCH_QUERY = "Adidas recent media coverage";
    private static final String API_URL = "https://www.googleapis.com/customsearch/v1";
    // Specify the start and end dates for the date range
    static LocalDate startDate = LocalDate.of(2024, 1, 1);
    static LocalDate endDate = LocalDate.of(2024, 2, 3);
    public static void main(String[] args) {
        try {
            String rssFeed = getGoogleSearchRssFeed();
            System.out.println("Google Search RSS Feed:\n" + rssFeed);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getGoogleSearchRssFeed() throws IOException, InterruptedException {
        String responseBody = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(buildRequestUrl()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                responseBody = response.body();
            } else {
                System.out.println("Error: " + response.statusCode());
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return responseBody;
    }

    private static String buildRequestUrl() throws UnsupportedEncodingException {
//        return API_URL + "?key=" + API_KEY + "&cx=" + CX_ID + "&q=" + SEARCH_QUERY + "&num=10&tbm=nws&output=rss";
        String encodedQuery = URLEncoder.encode(SEARCH_QUERY, StandardCharsets.UTF_8.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateRange = startDate.format(formatter) + "-" + endDate.format(formatter);

        String url = API_URL + "?key=" + API_KEY +
                "&cx=" + CX_ID +
                "&q=" + encodedQuery +
                "&num="+25+
                "&start="+1+
//                "&siteSearchFilter="+publicationsQuery+
                "&num=10&tbm=nws&output=rss" +
                "&dateRestrict=" + dateRange;
//        String url = API_URL + "?key=" + API_KEY +
//                encodedQuery +
//                "&num="+25+
//                "&start="+1+
//                "&cx=" + CX_ID +
////                "&siteSearchFilter="+publicationsQuery+
//                "&num=10&tbm=nws&output=rss" +
//                "&dateRestrict=" + dateRange;

        // Now use the 'url' for your API request.
        System.out.println(url);
        return url;
    }
}

