package com.example.this_just_in;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TrendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TrendingFragment())
                .commit();


    }


}
