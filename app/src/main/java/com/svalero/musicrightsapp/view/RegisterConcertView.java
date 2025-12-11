package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.contract.RegisterConcertContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.presenter.RegisterConcertPresenter;
import com.svalero.musicrightsapp.util.DateUtil;

import java.time.LocalDate;

public class RegisterConcertView extends AppCompatActivity implements RegisterConcertContract.View {

    RegisterConcertContract.Presenter presenter;
    private Boolean isEditMode = false;
    private long idConcertToEdit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_concert);

        presenter = new RegisterConcertPresenter(this);

        Intent intent = getIntent();

        //SI EL INTENT VIENE CON UN OBJETO, RELLENAMOS DATOS, PILLAMOS EL ID Y CAMBIAMOS EL BOTON:
        if (intent != null && intent.hasExtra("concert_data")) {
            isEditMode = true;
            Concert concert = (Concert) intent.getSerializableExtra("concert_data");

            idConcertToEdit = concert.getId();

            Button btn = findViewById(R.id.register_concert_button);
            btn.setText("Modificar");

            ((EditText) findViewById(R.id.concert_show_title)).setText(concert.getShowTitle());
            ((EditText) findViewById(R.id.concert_city)).setText(concert.getCity());
            ((EditText) findViewById(R.id.concert_province)).setText(concert.getProvince());
            ((EditText) findViewById(R.id.concert_date)).setText(DateUtil.formateDate(LocalDate.parse(concert.getDate())));
            ((EditText) findViewById(R.id.concert_status)).setText(concert.getStatus());
            CheckBox cbPerformed = findViewById(R.id.concert_performed);
            cbPerformed.setChecked(concert.getPerformed());
            ((EditText) findViewById(R.id.concert_ticket_price)).setText(String.valueOf(concert.getTicketPrice()));
        }
    }

    public void registerConcert(View view) {

        EditText etTitle = findViewById(R.id.concert_show_title);
        EditText etCity = findViewById(R.id.concert_city);
        EditText etProvince = findViewById(R.id.concert_province);
        EditText etDate = findViewById(R.id.concert_date);
        EditText etStatus = findViewById(R.id.concert_status);
        CheckBox cbPerformed = findViewById(R.id.concert_performed);        EditText etPrice = findViewById(R.id.concert_ticket_price);

        //PARSEO PARA ENVIAR LOS DATOS A LA API
        String title = etTitle.getText().toString();
        String city = etCity.getText().toString();
        String province = etProvince.getText().toString();
        String status = etStatus.getText().toString();
        LocalDate date = (etDate.getText().toString().isEmpty()) ? LocalDate.now() : DateUtil.parseDate(etDate.getText().toString());
        Boolean performed = cbPerformed.isChecked();
        Float price = (etPrice.getText().toString().isEmpty()) ? 0.0f : Float.parseFloat(etPrice.getText().toString());

        Float latitude = 0.0F;
        Float longitude = 0.0F;

        if (isEditMode) {
            presenter.modifyConcert(idConcertToEdit, title, city, province, date, status, performed, price);

        } else {
            long defaultMusicianId = 1L;
            Musician musician = new Musician();
            musician.setId(defaultMusicianId);

            presenter.registerConcert(title, city, province, date, status, performed, price, latitude, longitude, musician);
        }
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}