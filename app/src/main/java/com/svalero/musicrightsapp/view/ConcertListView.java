package com.svalero.musicrightsapp.view;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.adapter.ConcertAdapter;
import com.svalero.musicrightsapp.api.ConcertApi;
import com.svalero.musicrightsapp.api.ConcertApiInterface;
import com.svalero.musicrightsapp.contract.ConcertListContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.model.ConcertListModel;
import com.svalero.musicrightsapp.presenter.ConcertListPresenter;

import java.util.ArrayList;
import java.util.List;


public class ConcertListView extends AppCompatActivity implements ConcertListContract.View, ConcertAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ConcertAdapter concertAdapter;
    private List<Concert> concertList;
    private ConcertListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ConcertListPresenter(this);

        //Encontrar el RecyclerView en el XML
        recyclerView = findViewById(R.id.concert_list);

        //Decirle cómo se organizan los elementos (Verticalmente como una lista)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Inicializar la lista vacía para evitar errores
        concertList = new ArrayList<>();

        //Crear el adaptador y dárselo al RecyclerView (le pasamos this porque le dice le contexto)
        concertAdapter = new ConcertAdapter(this, concertList, this);
        recyclerView.setAdapter(concertAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //ACTIVAMOS EL presenter para que haga sus cosas llamando al model
        presenter.loadConcerts();
    }


    @Override
    public void showConcerts(List<Concert> concerts) {
            concertList.clear();
            concertList.addAll(concerts);
            concertAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetList() {
        presenter.loadConcerts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_concert) {
            Intent intent = new Intent(this, RegisterConcertView.class);
            startActivity(intent);

            return true;
        }
        return false;
    }


    @Override
    public void onEditClick(Concert concert) {
        Intent intent = new Intent(this, RegisterConcertView.class);
        intent.putExtra("concert_data", concert);

        startActivity(intent);
    }

    @Override
    public void onDeleteClick(long id) {
        presenter.deleteConcerts(id);
    }

    @Override
    public void onConcertDetailsClick(long id) {
        Intent intent = new Intent(this, DetailsConcertView.class);
        intent.putExtra("id", id);
    }
}

