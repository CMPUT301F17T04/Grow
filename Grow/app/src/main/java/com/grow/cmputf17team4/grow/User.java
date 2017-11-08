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
    private int[] followers;
    private int[] followings;

    public User() {}

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.numCompleted = 0;
        this.numMissed = 0;
        this.followers = new int[100];
        this.followings = new int[100];
        this.habitTypesList = new ArrayList<>();
        this.habitEventHistory = new ArrayList<>();
        this.habitEventToday = new ArrayList<>();
    }

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


    public int[] getFollowers() {
        return followers;
    }

    public void setFollowers(int[] followers) {
        this.followers = followers;
    }

    public int[] getFollowings() {
        return followings;
    }

    public void setFollowings(int[] followings) {
        this.followings = followings;
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
