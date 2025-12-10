package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.domain.Concert;

public class DetailsConcertView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concert_details);

        Intent intent = getIntent();

        Concert concert = (Concert) intent.getSerializableExtra("concert_data");

        ((TextView) findViewById(R.id.detail_show_title)).setText(concert.getShowTitle());
        ((TextView) findViewById(R.id.detail_location)).setText(concert.getCity());
        ((TextView) findViewById(R.id.detail_date)).setText(concert.getDate());
        ((TextView) findViewById(R.id.detail_price)).setText(String.valueOf(concert.getTicketPrice()));
        ((TextView) findViewById(R.id.detail_status)).setText(concert.getShowTitle());
    }
}
