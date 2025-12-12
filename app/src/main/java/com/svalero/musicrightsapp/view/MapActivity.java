package com.svalero.musicrightsapp.view;

import android.content.Intent;
import com.mapbox.geojson.Point;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.util.MapUtils;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mainMap);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        pointAnnotationManager = MapUtils.buildAnnotationManager(mapView);

        Intent intent = getIntent();
        Concert concert = (Concert)intent.getSerializableExtra("concert_data");
        showConcertMarker(concert);
    }


    private void showConcertMarker(Concert concert) {
        Point point = Point.fromLngLat(concert.getLongitude(), concert.getLatitude());
        MapUtils.getMarker(this, pointAnnotationManager, point, concert.getShowTitle());

        CameraOptions cameraOptions = new CameraOptions.Builder()
                .center(point)       // Centrar en el punto del concierto
                .zoom(9.0)          // Zoom nivel calle (ajusta entre 0 y 22)
                .pitch(0.0)          // Inclinación (0 es vista cenital)
                .bearing(0.0)        // Rotación (0 es Norte arriba)
                .build();
        mapView.getMapboxMap().setCamera(cameraOptions);
    }
}