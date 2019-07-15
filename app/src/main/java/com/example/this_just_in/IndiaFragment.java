package com.example.this_just_in;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndiaFragment extends Fragment {


    public IndiaFragment() {
        // Required empty public constructor
    }

    private NewsAdapter mAdapter;
    ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_india, container, false);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) rootView.findViewById(R.id.listview);

        String search_query = "https://newsapi.org/v2/top-headlines?country=in&language=en&apiKey=9d0fb4e298994103a35284846e2c40d9";
        // Create a new adapter that takes an empty list of books as input
        mAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());

        bookListView.setAdapter(mAdapter);

        NewsAsyncTask task = new NewsAsyncTask(getActivity(), rootView);
        task.execute(search_query);
        return rootView;

    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        private Context mContext;
        private View rootView;

        public NewsAsyncTask(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        protected void onPreExecute() {
            spinner = (ProgressBar) rootView.findViewById(R.id.progressBar);
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
            spinner = (ProgressBar) rootView.findViewById(R.id.progressBar);
            spinner.setVisibility(View.GONE);
            // If there is a valid list of books, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }

    }


}
