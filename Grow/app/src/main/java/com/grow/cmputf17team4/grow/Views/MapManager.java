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
import com.google.android.gms.maps.model.LatLng;
import com.grow.cmputf17team4.grow.R;

import org.osmdroid.util.GeoPoint;

/**
 * Created by charl on 2017/12/1.
 */

public class MapManager extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap Gmap;
    private GeoPoint currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.event_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        double latitude, longitude;
        latitude = 53.537519;
        longitude = -113.497412;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(latitude, longitude)), 10));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
