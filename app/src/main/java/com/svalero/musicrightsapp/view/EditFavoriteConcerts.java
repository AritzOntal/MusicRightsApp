package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.contract.EditFavoriteContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.presenter.EditFavoritePresenter;
// import com.svalero.musicrightsapp.util.DateUtil; // Descomenta si usas DateUtil

public class EditFavoriteConcerts extends AppCompatActivity implements EditFavoriteContract.View {

    private EditFavoritePresenter presenter;
    private Concert concert; // Objeto original para mantener el ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_concert);

        presenter = new EditFavoritePresenter(this, this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("concert_data")) {
            concert = (Concert) intent.getSerializableExtra("concert_data");

            cargarDatosEnPantalla();
        }

        Button btn = findViewById(R.id.register_concert_button);
        btn.setText("Guardar cambios");
        btn.setOnClickListener(v -> guardarCambios());
    }


    private void cargarDatosEnPantalla() {
        if (concert == null) return;

        ((EditText) findViewById(R.id.concert_show_title)).setText(concert.getShowTitle());
        ((EditText) findViewById(R.id.concert_city)).setText(concert.getCity());
        ((EditText) findViewById(R.id.concert_province)).setText(concert.getProvince());
        ((EditText) findViewById(R.id.concert_date)).setText(concert.getDate());
        ((EditText) findViewById(R.id.concert_status)).setText(concert.getStatus());

        //PROTEGEMOS POR SI HAY NULOS
        if (concert.getPerformed() != null) {
            ((CheckBox) findViewById(R.id.concert_performed)).setChecked(concert.getPerformed());
        }

        if (concert.getTicketPrice() != null) {
            ((EditText) findViewById(R.id.concert_ticket_price)).setText(String.valueOf(concert.getTicketPrice()));
        }
    }

    private void guardarCambios() {
        // RECOGEMOS LOS DATOS ESCRITOS
        EditText etTitle = findViewById(R.id.concert_show_title);
        EditText etCity = findViewById(R.id.concert_city);
        EditText etProvince = findViewById(R.id.concert_province);
        EditText etDate = findViewById(R.id.concert_date);
        EditText etStatus = findViewById(R.id.concert_status);
        CheckBox cbPerformed = findViewById(R.id.concert_performed);
        EditText etPrice = findViewById(R.id.concert_ticket_price);

        //LOS ACTUALIZAMOS
        concert.setShowTitle(etTitle.getText().toString());
        concert.setCity(etCity.getText().toString());
        concert.setProvince(etProvince.getText().toString());
        concert.setDate(etDate.getText().toString());
        concert.setStatus(etStatus.getText().toString());
        concert.setPerformed(cbPerformed.isChecked());
        String priceText = etPrice.getText().toString();
        concert.setTicketPrice(Float.parseFloat(priceText));
        presenter.updateConcert(concert);
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}