package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_concert);

        presenter = new RegisterConcertPresenter(this);
    }

    public void registerConcert(View view) {

        long musicianId = 1L;

        String title = ((EditText) findViewById(R.id.concert_show_title)).getText().toString();
        String city = ((EditText) findViewById(R.id.concert_city)).getText().toString();
        String province = ((EditText) findViewById(R.id.concert_province)).getText().toString();
        LocalDate date = DateUtil.parseDate(((EditText) findViewById(R.id.concert_date)).getText().toString());
        String status = ((EditText) findViewById(R.id.concert_status)).getText().toString();
        Boolean performed = Boolean.parseBoolean(((EditText) findViewById(R.id.concert_performed)).getText().toString());
        Float price = Float.parseFloat(((EditText) findViewById(R.id.concert_ticket_price)).getText().toString());
        Float latitude = 0.0F;
        Float longitude = 0.0F;

        Musician musician = new Musician();
        musician.setId(musicianId);

        presenter.registerConcert(title, city, province, date, status, performed, price, latitude, longitude, musician);
        }


    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish(); // Cerramos la pantalla para volver a la lista
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetForm() {

    }
}