package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Class represent a habit event
 * @author
 */

public class HabitEvent implements Identifiable,Comparable<HabitEvent>,GetImageAble {
    private String comment;
    private UUID uid;
    private String name;
    private String encodedImage;
    private Integer location;
    private Date date;
    private boolean changed;


    public HabitEvent(String name) {
        this.name = name;
        uid = generateUid();
        encodedImage = null;
        comment = "";
        location = 0;
        this.date = new Date();
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        setChanged();
    }

    public UUID getUid() {
        return uid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
    }

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

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }

    @Override
    public String getIndex() {
        return Constant.INDEX_HABIT_EVENT;
    }

    @Override
    public int compareTo(@NonNull HabitEvent o) {

        return(this.date.compareTo(o.date));
    }

    private void setChanged() {
        this.changed = true;
    }

    public boolean isChanged() {
        boolean result = changed;
        changed = false;
        return result;
    }

    public Date getDate() {
        return date;
    }

}
