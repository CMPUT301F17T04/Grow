package com.grow.cmputf17team4.grow.Views;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.SelfPosition;
import com.grow.cmputf17team4.grow.R;

import java.util.ArrayList;

/**
 * Created by charl on 2017/12/1.
 */

public class MapManager extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap Gmap;
    private ArrayList<HabitEvent> Habits = new ArrayList<HabitEvent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.event_map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Get the map view ready and self-positioning the camera.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        double latitude, longitude;

        latitude = 53.526624;
        longitude = -113.527080;
        /*
        Self positioning
         */
        try {
            SelfPosition locationListener = new SelfPosition();
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(latitude, longitude)), 10));
        Gmap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("You are Here"));

        MarkHabits();
    }

    /**
     * Add Habits to the map
     */
    public void MarkHabits(){

    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
