package com.example.this_just_in;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        News currentNews = (News) getIntent().getSerializableExtra("key");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

            ImageView imageImageView = findViewById(R.id.image);
            ImageLoader.getInstance().displayImage(currentNews.getUrlToImage(), imageImageView);

            TextView titleView = findViewById(R.id.title);
            titleView.setText(currentNews.getTitle());

            TextView sourceView = findViewById(R.id.source);
            sourceView.setText(currentNews.getName());

            TextView contentView = findViewById(R.id.content);
            contentView.setText(currentNews.getContent());

    }
}
