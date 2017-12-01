package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Date;
import java.util.UUID;

/**
 * Class represents a habit event
 * @author Yizhou Zhao
 */
public class HabitEvent implements Identifiable,Comparable<HabitEvent>,GetImageable {
    private String comment;
    private UUID uid;
    private String name;
    private String encodedImage;
    private Integer location;
    private Date date;
    /**
     * Constructor of the HabitEvent
     * @param name the name of the event
     */

    public HabitEvent(String name) {
        this.name = name;
        uid = generateUid();
        encodedImage = null;
        comment = "";
        location = 0;
        this.date = new Date();
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
     * get the id of the habit event
     * @return UUID represents the id
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * get the name of the habit event
     * @return string represent the name
     */
    public String getName() {
        return name;
    }
    /**
     * set the name of the habit event
     * @param name the name of the habit event
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * get the image attached to the habit event
     * @return the String represents the encodedImage
     */
    public String getEncodedImage() {
        return encodedImage;
    }

    public void setAttachedLocation(Boolean attached) {
        if (attached){
            location = 1;
        } else {
            location = 0;
        }
    }

    public Boolean isAttachedLocation() {
        return location!=0;
    }
    /**
     * Determine whether to attach the location or not.
     */
    public void setLocation(Integer location) {
        this.location = location;
    }
    /**
     * Generate the ID for the habit event
     * @return the generated id for the habit event
     */
    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }
    /**
     * get the index of the habit event
     * @return the constant of INDEX_HABIT_EVENT
     */
    @Override
    public String getIndex() {
        return Constant.INDEX_HABIT_EVENT;
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

    @Override
    public String jsonify() {
        return new Gson().toJson(this);
    }
}
