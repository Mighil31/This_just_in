package com.example.this_just_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView trending = findViewById(R.id.trending);
        TextView india = findViewById(R.id.india);

        trending.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent trendingIntent = new Intent(MainActivity.this, TrendingActivity.class);
                startActivity(trendingIntent);
            }
        });

        india.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent indiaIntent = new Intent(MainActivity.this, IndiaActivity.class);
                startActivity(indiaIntent);

            }
        });


    }


}
