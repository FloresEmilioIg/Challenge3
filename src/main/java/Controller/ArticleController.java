package Controller;

import Model.Article;
import View.ArticleView;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {

    private final String API_KEY = "22d39513fa0109a11b2d9d0a09c553203ac80715fee63e3f4b6c9a82fe5f3ee8"; // 🔑 API key goes here
    private ArticleView view;



    public ArticleController(ArticleView view) {
            this.view = view;
        }


    public void searchArticles(String query) {
        List<Article> articles = new ArrayList<>();

        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = "https://serpapi.com/search.json?engine=google_scholar&q="
                    + encodedQuery + "&hl=en&num=10&api_key=" + API_KEY;

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(url);
                String responseBody = client.execute(request, httpResponse ->
                        EntityUtils.toString(httpResponse.getEntity()));

                JSONObject json = new JSONObject(responseBody);

                if (json.has("organic_results")) {
                    JSONArray results = json.getJSONArray("organic_results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject obj = results.getJSONObject(i);

                        String title = obj.optString("title", "N/A");

                        // Autores y año
                        String authors = "N/A";
                        String year = "N/A";
                        if (obj.has("publication_info")) {
                            JSONObject pubInfo = obj.getJSONObject("publication_info");
                            authors = pubInfo.optString("summary", "N/A");
                            year = pubInfo.optString("year", "N/A");
                        }

                        // Citaciones
                        String citedBy = "0";
                        if (obj.has("inline_links")) {
                            JSONObject links = obj.getJSONObject("inline_links");
                            if (links.has("cited_by")) {
                                citedBy = links.getJSONObject("cited_by").optString("total", "0");
                            }
                        }

                        String link = obj.optString("link", "N/A");

                        articles.add(new Article(title, authors, year, citedBy, link));
                    }
                }

                view.displayArticles(articles);

            }
        } catch (IOException e) {
            view.displayError("Network error: " + e.getMessage());
        } catch (Exception e) {
            view.displayError("Unexpected error: " + e.getMessage());
        }
    }
}

