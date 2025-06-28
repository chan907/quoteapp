package com.example.quoteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    TextView quoteText;
    Button shareBtn, favBtn, viewFavBtn;
    String currentQuote;

    String[] quoteList = {
            "Believe in yourself.",
            "Stay positive and happy.",
            "Work hard and never give up.",
            "Your only limit is your mind.",
            "Dream big. Start small. Act now."
    };

    SharedPreferences prefs;
    Set<String> favQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        shareBtn = findViewById(R.id.shareBtn);
        favBtn = findViewById(R.id.favBtn);
        viewFavBtn = findViewById(R.id.viewFavBtn);

        prefs = getSharedPreferences("QuotesApp", MODE_PRIVATE);
        favQuotes = prefs.getStringSet("favorites", new HashSet<>());

        currentQuote = getDailyQuote();
        quoteText.setText(currentQuote);

        shareBtn.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
            startActivity(Intent.createChooser(shareIntent, "Share quote using"));
        });

        favBtn.setOnClickListener(v -> {
            favQuotes.add(currentQuote);
            prefs.edit().putStringSet("favorites", favQuotes).apply();
            Toast.makeText(this, "Added to favorites!", Toast.LENGTH_SHORT).show();
        });

        viewFavBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, FavoritesActivity.class));
        });
    }

    private String getDailyQuote() {
        int index = new Random().nextInt(quoteList.length);
        return quoteList[index];
    }
}
