package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.musicrightsapp.Db.AppDatabase;
import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.adapter.FavConcertsAdapter;
import com.svalero.musicrightsapp.contract.FavoriteConcertsContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.presenter.ConcertListPresenter;
import com.svalero.musicrightsapp.presenter.FavoriteConcertsPresenter;

import java.util.List;

public class DetailsConcertView extends AppCompatActivity implements FavoriteConcertsContract.View{

        private FavoriteConcertsPresenter presenter;

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

        presenter = new FavoriteConcertsPresenter(this, this);


        Button btnAddFav = findViewById(R.id.btn_add_fav);
        btnAddFav.setOnClickListener(v -> {
            savetToFavorites(concert);
        });

        Button btnViewMap = findViewById(R.id.btn_view_map);
        btnViewMap.setOnClickListener(v -> {
            Intent goMaps = new Intent(this, MapActivity.class);
            goMaps.putExtra("concert_data", concert);
            startActivity(goMaps);
        });
    }




    private void savetToFavorites (Concert concert) {
        presenter.addFavoriteConcert(concert);
    }

    @Override
    public void showFavoriteConcerts(List<Concert> concerts) {
        presenter.loadFavoriteConcerts();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
