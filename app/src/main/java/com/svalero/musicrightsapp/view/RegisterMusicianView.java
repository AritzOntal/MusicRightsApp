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
import com.svalero.musicrightsapp.contract.RegisterMusicianContract;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.presenter.RegisterMusicianPresenter;
import com.svalero.musicrightsapp.util.DateUtil;

import java.time.LocalDate;

public class RegisterMusicianView extends AppCompatActivity implements RegisterMusicianContract.View {

    RegisterMusicianContract.Presenter presenter;
    private Boolean isEditMode = false;
    private long idMusicianToEdit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_musician);

        presenter = new RegisterMusicianPresenter(this);

        Intent intent = getIntent();

        // SI EL INTENT VIENE CON UN OBJETO, RELLENAMOS DATOS, PILLAMOS EL ID Y CAMBIAMOS EL BOTON:
        if (intent != null && intent.hasExtra("musician_data")) {
            isEditMode = true;
            Musician musician = (Musician) intent.getSerializableExtra("musician_data");

            if (musician != null) {
                idMusicianToEdit = musician.getId();

                Button btn = findViewById(R.id.register_musician_button);
                btn.setText("Modificar");

                ((EditText) findViewById(R.id.musician_name)).setText(musician.getFirstName());
                ((EditText) findViewById(R.id.musician_surname)).setText(musician.getLastName());
                ((EditText) findViewById(R.id.musician_dni)).setText(musician.getDni());
                ((EditText) findViewById(R.id.musician_birth_date)).setText(DateUtil.formateDate(LocalDate.parse(musician.getBirthDate())));
                ((EditText) findViewById(R.id.musician_performance_fee)).setText(String.valueOf(musician.getPerformanceFee()));
                ((EditText) findViewById(R.id.musician_affiliated_number)).setText(String.valueOf(musician.getAffiliatedNumber()));

                CheckBox cbAffiliated = findViewById(R.id.musician_affiliated);
                cbAffiliated.setChecked(musician.getAffiliated());
            }
        }
    }

    public void registerMusician(View view) {

        EditText etName = findViewById(R.id.musician_name);
        EditText etSurname = findViewById(R.id.musician_surname);
        EditText etDni = findViewById(R.id.musician_dni);
        EditText etBirthDate = findViewById(R.id.musician_birth_date);
        EditText etFee = findViewById(R.id.musician_performance_fee);
        EditText etAffiliatedNumber = findViewById(R.id.musician_affiliated_number);
        CheckBox cbAffiliated = findViewById(R.id.musician_affiliated);

        // PARSEO PARA ENVIAR LOS DATOS A LA API
        String firstName = etName.getText().toString();
        String lastName = etSurname.getText().toString();
        String dni = etDni.getText().toString();
        String birthDate = (etBirthDate.getText().toString());
        Boolean affiliated = cbAffiliated.isChecked();

        // TERNARIO POR SI VIENE VACIO
        Float performanceFee = (etFee.getText().toString().isEmpty()) ? 0.0f : Float.parseFloat(etFee.getText().toString());
        Long affiliatedNumber = (etAffiliatedNumber.getText().toString().isEmpty()) ? 0L : Long.parseLong(etAffiliatedNumber.getText().toString());

        if (isEditMode) {
            presenter.modifyMusician(idMusicianToEdit, firstName, lastName, birthDate, affiliated, dni, performanceFee, affiliatedNumber);

        } else {
            Musician newMusician = Musician.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthDate(birthDate)
                    .dni(dni)
                    .affiliated(affiliated)
                    .performanceFee(performanceFee)
                    .affiliatedNumber(affiliatedNumber)
                    .build();

            presenter.registerMusician(newMusician);
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