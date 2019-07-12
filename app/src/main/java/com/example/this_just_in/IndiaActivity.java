package com.example.this_just_in;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class IndiaActivity extends AppCompatActivity {

    private NewsAdapter mAdapter;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);



        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.listview);

        String search_query = "https://newsapi.org/v2/top-headlines?country=in&language=en&apiKey=9d0fb4e298994103a35284846e2c40d9";
        // Create a new adapter that takes an empty list of books as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        bookListView.setAdapter(mAdapter);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(search_query);


    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {


        @Override
        protected void onPreExecute() {
            spinner = (ProgressBar)findViewById(R.id.progressBar);
            spinner.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected List<News> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<News> result = Utils.fetchNewsData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<News> data) {
            // Clear the adapter of previous book data
            mAdapter.clear();
            spinner = (ProgressBar)findViewById(R.id.progressBar);
            spinner.setVisibility(View.GONE);
            // If there is a valid list of books, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }

    }

}
