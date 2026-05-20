package com.basico91.inmobiliariatransversal.menu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maplibre.android.annotations.MarkerOptions;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;

public class InicioViewModel extends AndroidViewModel {

    MutableLiveData<MapaActual> mapaActualM = new MutableLiveData<>();
    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMapaActual(){
        return mapaActualM;
    }

    public void  cargarMapa(){
        MapaActual mapa = new MapaActual();
        mapaActualM.setValue(mapa );
    }

    public static class MapaActual implements OnMapReadyCallback{

        public LatLng SanLuis = new LatLng(-33.288576, -66.322482);
        @Override
        public void onMapReady(@NonNull MapLibreMap mapLibreMap) {
            mapLibreMap.setStyle("", new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    mapLibreMap.addMarker(new MarkerOptions().position(SanLuis).title("San Luis"));

                    CameraPosition posicionDeLaCam = new CameraPosition.Builder()
                            .target(SanLuis)
                            .zoom(4)
                            .bearing(0)
                            .tilt(0)
                            .build();

                            mapLibreMap.setCameraPosition(posicionDeLaCam);
                }
            });
        }
    }
}
