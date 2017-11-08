package com.grow.cmputf17team4.grow;

import com.grow.cmputf17team4.grow.Models.HabitType;

import java.util.Date;

/**
 * Class represent a habit event
 * @author
 */

public class HabitEvent {
    private int id;
    private int userId;
    private Date startTime;
    private Date endTime;
    private String dateToRepresent;
    private String comment;
    private boolean finished;
    private double duration;
    private byte[] photo;
    private HabitType type;

    public HabitEvent(int userId){
        this.id = 1;
        this.userId = userId;
        this.startTime = new Date(2017,10,22);
        //this.endTime = getEndTime();
        this.dateToRepresent = "2017-10-22";
        this.comment = "zhai is dead";
        this.finished = false;
        this.duration = 10;
        this.photo = new byte[5];


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDateToRepresent() {
        return dateToRepresent;
    }

    public void setDateToRepresent(String dateToRepresent) {
        this.dateToRepresent = dateToRepresent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setType(HabitType type){
        this.type = type;
    }

    public HabitType getType(){
        return this.type;
    }
}
