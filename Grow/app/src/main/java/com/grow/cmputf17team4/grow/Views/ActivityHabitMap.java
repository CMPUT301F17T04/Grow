package com.grow.cmputf17team4.grow.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.grow.cmputf17team4.grow.Controllers.MapManager;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.SelfPosition;
import com.grow.cmputf17team4.grow.R;

import java.util.ArrayList;

/**
 * Activity shows the map
 * @author Yizhou Zhao
 */

public class ActivityHabitMap extends AppCompatActivity implements OnMapReadyCallback {

    /*
    GoogleMap Gmap;

    private Location mLocation;
    private Location mLastKnownLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mLocationPermissionGranted = false;
    */

    private GoogleMap mMap;

    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private CameraPosition mCameraPosition;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted = false;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;
    private Location mLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;

    // Used for requirement
    private ArrayList<HabitEvent> habits = new ArrayList<HabitEvent>();
    // true: intent from mine event list; false: intent from community event list
    private boolean intentFrom;
    private boolean requestSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get extra from intent
        Intent intent = getIntent();
        intentFrom = intent.getBooleanExtra("intentFrom", true);


        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        setContentView(R.layout.activity_habit_map);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.event_map);
        mapFragment.getMapAsync(this);

        // Get habit event list to print on the map
        habits = new ArrayList<HabitEvent>();
        MapManager mapManager = new MapManager();
        mapManager = MapManager.getInstance();
        habits.addAll(mapManager.getHabitEventList());

        Log.d("googlemap", "Finished OnCreate()");

    }

    /**
     * Get the map view ready and self-positioning the camera.
     * @param map
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;


        // Prompt the user for permission.
        while(!mLocationPermissionGranted) {
            getLocationPermission();
        }
        // Turn on the My Location layer and the related control on the map.

            updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device
                            mLastKnownLocation = task.getResult();
                            mLocation = mLastKnownLocation;
                            Log.d("googlemap", "got current location");
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            AddHabitEventTomMap();
                        } else {
                            Log.d("googlemap", "Current location is null. Using defaults.");
                            Log.e("googlemap", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            if(!requestSent) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                requestSent = true;
            }
        }
    }


    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

        updateLocationUI();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                //getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Add habit event to map
     */
    public void AddHabitEventTomMap(){
        for(HabitEvent habit : habits){

            Log.d("googlemap",habit.getComment());
            Location tempLocation = habit.getHabitLocation();
            double lat = tempLocation.getLatitude();
            double lng = tempLocation.getLongitude();
            Log.d("googlemap", Double.toString(lat));
            Log.d("googlemap", Double.toString(lng));
            LatLng position = new LatLng(lat, lng);

            boolean fiveKMClose = isFiveKm(tempLocation, mLocation);
            if(intentFrom) {
                if (fiveKMClose) {
                    mMap.addMarker(new MarkerOptions().position(position)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                            .title(habit.getName())                 // Here, we put the Habit event name and user name
                            .snippet(habit.getComment()));       // Here, we put the comment
                } else {
                    mMap.addMarker(new MarkerOptions().position(position)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .title(habit.getName())
                            .snippet(habit.getComment()));
                }
            }else{
                if (fiveKMClose) {
                    mMap.addMarker(new MarkerOptions().position(position)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                            .title(habit.getName())                 // Here, we put the Habit event name and user name
                            .snippet(habit.getUserId()));       // Here, we put the comment
                } else {
                    mMap.addMarker(new MarkerOptions().position(position)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .title(habit.getName())

                            .snippet(habit.getUserId()));
                }
            }

        }
    }

    /**
     * Check if distance between my location and event location is less then 5 km
     * @param mLocation my location
     * @param eLocation event location
     * @return True if less then 5km, False otherwise
     */
    public static boolean isFiveKm(Location mLocation, Location eLocation){
        boolean returnValue = true;

        double mlat = Math.toRadians(mLocation.getLatitude());
        double mlong = Math.toRadians(mLocation.getLongitude());
        double elat = Math.toRadians(eLocation.getLatitude());
        double elong = Math.toRadians(eLocation.getLongitude());

        // Using Tunnel distance method to calculate geographical distance
        double dx = Math.cos(elat)*Math.cos(elong) - Math.cos(mlat)*Math.cos(mlong);
        double dy = Math.cos(elat)*Math.sin(elong) - Math.cos(mlat)*Math.sin(mlong);
        double dz = Math.sin(elat) - Math.sin(mlat);

        double ch = Math.sqrt(dx*dx + dy*dy + dz*dz);
        double distance = 6371*ch;

        if(distance >= 5){
            returnValue = false;
        }
        return returnValue;
    }
}