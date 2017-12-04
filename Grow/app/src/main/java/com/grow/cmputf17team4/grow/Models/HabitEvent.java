package com.grow.cmputf17team4.grow.Models;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.BoringLayout;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.osmdroid.util.GeoPoint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import com.grow.cmputf17team4.grow.Models.SelfPosition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.SelfPosition;
import com.grow.cmputf17team4.grow.Views.ActivityMain;

/**
 * Class represents a habit event
 * @author Yizhou Zhao
 */
public class HabitEvent extends Item implements Comparable<HabitEvent>,GetImageable {
    private String comment;
    private String habitTypeID;
    private String encodedImage;
    private Location HabitLocation;
    private Date date;
    private String prevEvent;
    private String nextEvent;
    private String userId;

    private String name;

    /**
     * Constructor of the HabitEvent
     * @param habitTypeID the id of the habit type which the event belongs to
     */
    public HabitEvent(String habitTypeID,String userId) {
        this.habitTypeID = habitTypeID;
        uid = generateUid();
        encodedImage = null;
        comment = "";
        HabitLocation = null;
        this.date = new Date();
        this.type = Constant.TYPE_HABIT_EVENT;
        prevEvent = null;
        nextEvent = null;
        this.userId = userId;
    }

    /**
     * Attach an image to a habit event
     * @param encodedImage the image to be added
     */
    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
    /**
     * get comment of the habit event
     * @return the comment of the habit event
     */
    public String getComment() {
        return comment;
    }
    /**
     * set the comment of the habit event
     * @param comment a string represents comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * get the name of the habit event
     * @return string represent the name
     */
    public String getName() {
        HabitType habitType = this.getHabitType();
        if (habitType != null){
            return habitType.getName();
        } else {
            return this.name;
        }
    }

    /**
     * get the image attached to the habit event
     * @return the String represents the encodedImage
     */
    @Override
    public String getEncodedImage() {
        return encodedImage;
    }

    public Location getHabitLocation(){ return HabitLocation;}

    public void setAttachedLocation(Boolean attached, Activity activity) {
        if (attached){
            getDeviceLocation(activity);
        } else {
            HabitLocation = null;
        }
    }

    public Boolean isAttachedLocation() {
        return HabitLocation!=null;
    }


    /**
     * get the difference between this habit event with given habit event
     * @param o the habit event that will be compared with
     * @return the difference of time
     */
    @Override
    public int compareTo(@NonNull HabitEvent o) {
        if (Constant.TIME_FORMAT.format(this.getDate()).equals(Constant.TIME_FORMAT.format(o.getDate()))){
            return - this.getName().compareTo(o.getName());
        }
        return(this.date.compareTo(o.date));
    }

    /**
     * get the date of the this habit event
     * @return the date of this habit event occurred
     */
    public Date getDate() {
        return date;
    }



    public String getPrevEvent() {
        return prevEvent;
    }

    public void setPrevEvent(String prevEvent) {
        this.prevEvent = prevEvent;
    }

    public String getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(String nextEvent) {
        this.nextEvent = nextEvent;
    }


    public HabitType getHabitType(){
        try {
            return DataManager.getInstance().getHabitList().get(this.habitTypeID);
        } catch (NullPointerException e){
            return null;
        }
    }
    @VisibleForTesting
    public void setDateForTestingOnly(Date date) {
        this.date = date;
    }

    public String getHabitTypeID() {
        return habitTypeID;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation(Activity activity) {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        FusedLocationProviderClient mFusedLocationProviderClient;
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        try {
            Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(activity, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device
                        HabitLocation = task.getResult();
                        Log.d("googlemap", "got current location");
                        DataManager.getInstance().getEventList().commit(getUid());

                    } else {
                        Log.d("googlemap", "Current location is null. Using defaults.");
                        Log.e("googlemap", "Exception: %s", task.getException());
                    }
                }
            });
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
