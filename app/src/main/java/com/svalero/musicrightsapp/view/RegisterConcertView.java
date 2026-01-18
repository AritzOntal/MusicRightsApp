package com.svalero.musicrightsapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.contract.RegisterConcertContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.presenter.RegisterConcertPresenter;
import com.svalero.musicrightsapp.util.DateUtil;
import com.svalero.musicrightsapp.util.MapUtils;

import java.time.LocalDate;

public class RegisterConcertView extends AppCompatActivity implements RegisterConcertContract.View, OnMapClickListener {

    private RegisterConcertContract.Presenter presenter;
    private Boolean isEditMode = false;
    private long idConcertToEdit = 0;
    private MapView mapView;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_concert);

        //CARGAMOS EL MAPA
        mapView = findViewById(R.id.mainMap);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        initializeGesturesPlugin();
        pointAnnotationManager = MapUtils.buildAnnotationManager(mapView);

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

            Point point = Point.fromLngLat(concert.getLongitude(), concert.getLatitude());
            currentPoint = point;

            MapUtils.getMarker(this, pointAnnotationManager, point, concert.getShowTitle());
            CameraOptions cameraOptions = new CameraOptions.Builder()
                    .center(point)
                    .zoom(9.0)
                    .pitch(0.0)
                    .bearing(0.0)
                    .build();
            mapView.getMapboxMap().setCamera(cameraOptions);
        }
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        MapUtils.addMarker(this, pointAnnotationManager, point);
        currentPoint = point;
        return false;
    }

    public void registerConcert(View view) {

        EditText etTitle = findViewById(R.id.concert_show_title);
        EditText etCity = findViewById(R.id.concert_city);
        EditText etProvince = findViewById(R.id.concert_province);
        EditText etDate = findViewById(R.id.concert_date);
        EditText etStatus = findViewById(R.id.concert_status);
        CheckBox cbPerformed = findViewById(R.id.concert_performed);
        EditText etPrice = findViewById(R.id.concert_ticket_price);


        //PARSEO PARA ENVIAR LOS DATOS A LA API
        String title = etTitle.getText().toString();
        String city = etCity.getText().toString();
        String province = etProvince.getText().toString();
        String status = etStatus.getText().toString();
        Boolean performed = cbPerformed.isChecked();
        String dateStr = etDate.getText().toString();
        Float price = (etPrice.getText().toString().isEmpty()) ? 0.0f : Float.parseFloat(etPrice.getText().toString());

        if (title.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "El título y la ciudad son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!dateStr.isEmpty() && !dateStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
            Toast.makeText(this, "Formato de fecha incorrecto. Usa: dd-MM-yyyy", Toast.LENGTH_SHORT).show();
            return;
        }

        if (price < 0) {
            Toast.makeText(this, "El precio no puede ser negativo", Toast.LENGTH_SHORT).show();
            return;
        }

        LocalDate date = (etDate.getText().toString().isEmpty()) ? LocalDate.now() : DateUtil.parseDate(etDate.getText().toString());

        if (date.isAfter(LocalDate.now())) {
            Toast.makeText(this, "La fecha del concierto deber ser antes que hoy", Toast.LENGTH_SHORT).show();
            return;
        }

        // Detenemos si currentPoint es nulo
        if (currentPoint == null) {
            Toast.makeText(this, "Por favor, selecciona una ubicación en el mapa", Toast.LENGTH_SHORT).show();
            return;
        }

        //PILLAMOS POSICION DEL POINT
        Double latitude = currentPoint.latitude();
        Double longitude = currentPoint.longitude();


        if (isEditMode) {
            presenter.modifyConcert(idConcertToEdit, title, city, province, date, status, performed, price, latitude, longitude);

        } else {
            long defaultMusicianId = 18L;
            Musician musician = new Musician();
            musician.setId(defaultMusicianId);

            presenter.registerConcert(title, city, province, date, status, performed, price, latitude, longitude, musician);
        }
    }

    @Override
    public void showSuccessMessage(String message) {
        pointAnnotationManager.deleteAll();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void  initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

}