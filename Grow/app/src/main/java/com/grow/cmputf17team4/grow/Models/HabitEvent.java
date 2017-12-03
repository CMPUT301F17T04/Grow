package com.grow.cmputf17team4.grow.Models;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.BoringLayout;

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

    /**
     * Constructor of the HabitEvent
     * @param habitTypeID the id of the habit type which the event belongs to
     */

    public HabitEvent(String habitTypeID) {
        this.habitTypeID = habitTypeID;
        uid = generateUid();
        encodedImage = null;
        comment = "";
        HabitLocation = null;
        this.date = new Date();
        this.type = Constant.TYPE_HABIT_EVENT;
        prevEvent = null;
        nextEvent = null;
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
        return this.getHabitType().getName();
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

    public void setAttachedLocation(Boolean attached, Context context) {
        if (attached){
            try {
                SelfPosition locationListener = new SelfPosition();
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                this.HabitLocation = location;
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {
            HabitLocation = null;
        }
    }

    public Boolean isAttachedLocation() {
        return HabitLocation!=null;
    }
    /**
     * Determine whether to attach the location or not.
     */
    public void setLocation(Location location) {
        this.HabitLocation = location;
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

    /**
     * Get the date in format HH:mm MM/dd/yyyy
     * @return
     */
    public String getStringDate(){
        String pattern = "HH:mm  MM/dd/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateString = format.format(date);

        return dateString;
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
        return DataManager.getInstance().getHabitList().get(this.habitTypeID);
    }
    @VisibleForTesting
    public void setDateForTestingOnly(Date date) {
        this.date = date;
    }
}
