package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.adapter.ConcertAdapter;
import com.svalero.musicrightsapp.adapter.FavConcertsAdapter;
import com.svalero.musicrightsapp.contract.ConcertListContract;
import com.svalero.musicrightsapp.contract.FavoriteConcertsContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.presenter.ConcertListPresenter;
import com.svalero.musicrightsapp.presenter.FavoriteConcertsPresenter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteConcertsView extends AppCompatActivity implements FavoriteConcertsContract.View, FavConcertsAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<Concert> favConcertList;
    private FavConcertsAdapter favConcertsAdapter;
    private FavoriteConcertsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_concerts);

        recyclerView = findViewById(R.id.rvFavoriteConcerts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favConcertList = new ArrayList<>();

        favConcertsAdapter = new FavConcertsAdapter(this, favConcertList, this);
        recyclerView.setAdapter(favConcertsAdapter);

        presenter = new FavoriteConcertsPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadFavoriteConcerts();
    }

    @Override
    public void showFavoriteConcerts(List<Concert> concerts) {
        favConcertList.clear();
        favConcertList.addAll(concerts);
        favConcertsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        presenter.loadFavoriteConcerts();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClick(Concert concert) {
        Intent intent = new Intent(this, EditFavoriteConcerts.class);
        intent.putExtra("concert_data", concert);

        startActivity(intent);
    }

    @Override
    public void onDeleteClick(long id) {
        presenter.deleteFavoriteConcert(id);
    }

    @Override
    public void onConcertDetailsClick(Concert concert) {

    }
}
