package com.grow.cmputf17team4.grow.Models;

import android.graphics.Bitmap;

import java.util.Observable;
import java.util.UUID;

/**
 * Class represent a habit event
 * @author
 */

public class HabitEvent extends Observable implements Identifiable {
    private String comment;
    private UUID uid;
    private String name;
    private Bitmap image;

    public HabitEvent(String name) {
        this.name = name;
        uid = generateUid();
        image = null;
        comment = "";
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
        setChanged();
    }

    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }

    @Override
    public String getIndex() {
        return Constant.INDEX_HABIT_EVENT;
    }
}
