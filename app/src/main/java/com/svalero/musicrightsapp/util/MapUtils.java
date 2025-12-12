package com.svalero.musicrightsapp.util;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.musicrightsapp.R;

public class MapUtils {

    public static PointAnnotationManager buildAnnotationManager(MapView mapView) {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        return PointAnnotationManagerKt.createPointAnnotationManager(
                annotationPlugin, annotationConfig);
    }


    //TE LLEVA AL MARCADOR EN EL MAPA
    public static void getMarker(Context context, PointAnnotationManager pointAnnotationManager,
                                 Point point, String message) {
        PointAnnotationOptions annotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(context.getResources(), R.mipmap.red_marker_foreground))
                .withTextField(message);
        pointAnnotationManager.create(annotationOptions);
    }


    //AÑADIR MARCADOR PILLANDO LA PULSACION
    public static void addMarker(Context context, PointAnnotationManager pointAnnotationManager, Point point) {
        getMarker(context, pointAnnotationManager, point, context.getResources().getString(R.string.here));
    }
}