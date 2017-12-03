package com.grow.cmputf17team4.grow.Controllers;

import android.location.Location;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;

/**
 * Created by Yizhou Zhao
 */

public class MapManager {
    private ArrayList<Location> locationToPrint;

    public MapManager(){
        locationToPrint = new ArrayList<Location>();
    }

    public MapManager(ArrayList<Location> locations){
        locationToPrint.addAll(locations);
    }

    /**
     * Check if distance between my location and event location is less then 5 km
     * @param mLocation my location
     * @param eLocation event location
     * @return True if less then 5km, False otherwise
     */
    public boolean isFiveKm(Location mLocation, Location eLocation){
        boolean returnValue = true;

        double mlat = mLocation.getLatitude();
        double mlong = mLocation.getLongitude();
        double elat = eLocation.getLatitude();
        double elong = eLocation.getLongitude();

        // Using Tunnel distance method to calculate geographical distance
        double dx;
        double dy;
        double dz;

        double ch;


        return returnValue;
    }
}
