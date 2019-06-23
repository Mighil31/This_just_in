package com.example.this_just_in;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        News currentNews = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.title);
        titleTextView.setText(currentNews.getTitle());

        TextView nameTextView = listItemView.findViewById(R.id.name);
        nameTextView.setText(currentNews.getName());

        TextView publishTextView = listItemView.findViewById(R.id.publishedAt);
        publishTextView.setText(currentNews.getPublishedAt());

        ImageView imageImageView = listItemView.findViewById(R.id.image);
        imageImageView.setImageResource(R.drawable.content);

        return listItemView;

    }
}
