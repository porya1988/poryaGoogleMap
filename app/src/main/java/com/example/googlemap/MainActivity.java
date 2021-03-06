package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ConcurrentMap;

public class MainActivity extends AppCompatActivity {
     double latitude,longitude;
     SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng home=new LatLng(34.2887711,47.0389857);
                MarkerOptions phomePointer=new MarkerOptions().position(home).
                        title("Porya Home").snippet("working").icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                googleMap.addMarker(phomePointer);

                LatLng azadegan=new LatLng(34.2887711,47.0265376);
                MarkerOptions azadeganSquare=new MarkerOptions().position(azadegan)
                        .title("Square").icon(BitmapDescriptorFactory.fromResource(R.drawable.location));

                googleMap.addMarker(azadeganSquare);

                CameraPosition cameraPosition=new CameraPosition.Builder().
                        target(home).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

               // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
               // googleMap.getUiSettings().setZoomControlsEnabled(true);
                //googleMap.setMyLocationEnabled(true);


            }
        });


        GPSTracker gpsTracker=new GPSTracker(getApplicationContext());

        if(gpsTracker.canGetLocation){
            latitude=gpsTracker.getLatitude();
            longitude=gpsTracker.getLongitude();
            Log.e("","");
        }else{

            gpsTracker.showSettingsAlert();
        }
    }
}