package com.example.this_just_in;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> implements Serializable {

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

         final News currentNews = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.title);
        titleTextView.setText(currentNews.getTitle());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(view.getContext(), DetailActivity.class);
                newIntent.putExtra("key", currentNews);
                getContext().startActivity(newIntent);
            }
        });

        TextView nameTextView = listItemView.findViewById(R.id.name);
        nameTextView.setText(currentNews.getName());

        TextView publishTextView = listItemView.findViewById(R.id.publishedAt);
        publishTextView.setText(currentNews.getPublishedAt());

        ImageView imageImageView = listItemView.findViewById(R.id.image);
        ImageLoader.getInstance().displayImage(currentNews.getUrlToImage(), imageImageView);

        return listItemView;

    }
}
