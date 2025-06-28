package com.example.quoteapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class FavoritesActivity extends AppCompatActivity {

    ListView favList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favList = findViewById(R.id.favList);

        SharedPreferences prefs = getSharedPreferences("QuotesApp", MODE_PRIVATE);
        Set<String> favorites = prefs.getStringSet("favorites", new HashSet<>());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>(favorites));

        favList.setAdapter(adapter);
    }
}
