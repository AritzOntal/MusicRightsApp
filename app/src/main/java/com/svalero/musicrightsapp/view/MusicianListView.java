package com.svalero.musicrightsapp.view;

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
import com.svalero.musicrightsapp.adapter.MusicianAdapter;
import com.svalero.musicrightsapp.contract.MusicianListContract;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.presenter.MusicianListPresenter;

import java.util.ArrayList;
import java.util.List;

public class MusicianListView extends AppCompatActivity implements MusicianListContract.View, MusicianAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MusicianAdapter musicianAdapter;
    private List<Musician> musicianList;
    private MusicianListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_list);

        presenter = new MusicianListPresenter(this);

        // Encontrar el RecyclerView en el XML
        recyclerView = findViewById(R.id.musician_list);

        // Decirle cómo se organizan los elementos (Verticalmente como una lista)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista vacía para evitar errores
        musicianList = new ArrayList<>();

        // Crear el adaptador y dárselo al RecyclerView.
        // Pasamos 'this' dos veces: una como Contexto y otra como Listener (OnItemClickListener)
        musicianAdapter = new MusicianAdapter(this, musicianList, this);
        recyclerView.setAdapter(musicianAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ACTIVAMOS EL presenter para que cargue los músicos cada vez que aparezca la pantalla
        presenter.loadMusicians();
    }

    @Override
    public void showMusicians(List<Musician> musicians) {
        if (musicians == null) {
            return;
        }

        musicianList.clear();
        musicianList.addAll(musicians);
        musicianAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void resetList() {
        presenter.loadMusicians();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_concert) {
            Intent intent = new Intent(this, RegisterMusicianView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onEditClick(Musician musician) {
        Intent intent = new Intent(this, RegisterMusicianView.class);
        intent.putExtra("musician_data", musician);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(long id) {
        presenter.deleteMusician(id);
    }
}