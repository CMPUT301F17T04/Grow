package com.grow.cmputf17team4.grow;

import java.util.ArrayList;

/**
 * Class to represent a user
 * @author
 */

public class User {
    private String name;
    private int id;
    private int numCompleted;
    private int numMissed;
    private ArrayList<HabitType> habitTypesList;
    private ArrayList<HabitEvent> habitEventHistory;
    private ArrayList<HabitEvent> habitEventToday;
    private int[] follwers;
    private int[] folloings;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCompleted() {
        return numCompleted;
    }

    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    public int getNumMissed() {
        return numMissed;
    }

    public void setNumMissed(int numMissed) {
        this.numMissed = numMissed;
    }

    public ArrayList<HabitType> getHabitTypesList() {
        return habitTypesList;
    }

    public void setHabitTypesList(ArrayList<HabitType> habitTypesList) {
        this.habitTypesList = habitTypesList;
    }


    public int[] getFollwers() {
        return follwers;
    }

    public void setFollwers(int[] follwers) {
        this.follwers = follwers;
    }

    public int[] getFolloings() {
        return folloings;
    }

    public void setFolloings(int[] folloings) {
        this.folloings = folloings;
    }

    public ArrayList<HabitEvent> getHabitEventHistory() {
        return habitEventHistory;
    }

    public void setHabitEventHistory(ArrayList<HabitEvent> habitEventHistory) {
        this.habitEventHistory = habitEventHistory;
    }

    public ArrayList<HabitEvent> getHabitEventToday() {
        return habitEventToday;
    }

    public void setHabitEventToday(ArrayList<HabitEvent> habitEventToday) {
        this.habitEventToday = habitEventToday;
    }
}
