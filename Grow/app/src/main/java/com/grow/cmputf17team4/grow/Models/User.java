package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Class to represent a user
 * @author not yet implement
 */

public class User extends Item implements GetImageable{
    private ArrayList<String> habitList;
    private String encodedImage;
    private String date;
    public User(String uid) {
        this.uid = uid;
        this.type = Constant.TYPE_USER;
        this.habitList = new ArrayList<>();
        this.encodedImage = null;
        this.date = Constant.TIME_FORMAT.format(new Date());
    }

    @Override
    public String getEncodedImage() {
        return encodedImage;
    }

    @Override
    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
        commit();
        DataManager.save();
    }

    public void newHabit(String habitId){
        this.habitList.add(habitId);
        commit();
    }

    public void deleteHabit(String habitId){
        this.habitList.remove(habitId);
        commit();
    }

    public String getDate() {
        return date;
    }

    private void commit(){
        DataManager.getInstance().getBuffer().update(Constant.QUERY_UPDATE,this);
    }

    public ArrayList<String> getHabitList() {
        return habitList;
    }
}
