package com.example.this_just_in;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private NewsAdapter mAdapter;
    ProgressBar spinner;


    public static ArrayList<News> extractNews(String newsJSON) {

        // If JSON String is empty return early
        if(TextUtils.isEmpty(newsJSON)){
            return null;
        }

        ArrayList<News> news = new ArrayList<>();

        try {
            String name;
            String title;
            String url;
            String urlToImage;
            String publishedAt;
            String content;

            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray articlesArray = baseJsonResponse.getJSONArray("articles");

            for(int i = 0;i < articlesArray.length(); i++){
                JSONObject currentNews = articlesArray.getJSONObject(i);
                JSONObject source = currentNews.getJSONObject("source");

                content = currentNews.getString("content");
                name = source.getString("name");
                title = currentNews.getString("title");
                urlToImage = currentNews.getString("urlToImage");
                publishedAt = currentNews.getString("publishedAt");

                News newsitem = new News(title, name, publishedAt, urlToImage, content);
                news.add(newsitem);
            }

        }
        catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing JSON results", e);

        }

        return news;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch(MalformedURLException e) {
            Log.e("P", "Problem building the code", e);
        }
        return url;
    }


    private static String makeHttpRequest (URL url) throws IOException {
        String jsonResponse = "";


        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Success code is 200
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("P", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("P", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("P", "Problem making the HTTP request.", e);
        }


        List<News> news = extractNews(jsonResponse);

        return news;
    }


    public class JSONTask extends AsyncTask<URL, String, String> {

        @Override
        protected String doInBackground(URL... urls) {
            return null;
        }
    }





}
