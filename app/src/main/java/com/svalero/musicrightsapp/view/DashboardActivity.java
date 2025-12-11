package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.musicrightsapp.R;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnMuscians = findViewById(R.id.musicians_button);
        Button btnConcerts = findViewById(R.id.concerts_button);

        btnMuscians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MusicianListView.class);
                startActivity(intent);
            }
        });

        btnConcerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ConcertListView.class);
                startActivity(intent);
            }
        });

        Button btnFavorites = findViewById(R.id.favorites_button);

        btnFavorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, FavoriteConcertsView.class);
                startActivity(intent);
            }
        });
     }
}
