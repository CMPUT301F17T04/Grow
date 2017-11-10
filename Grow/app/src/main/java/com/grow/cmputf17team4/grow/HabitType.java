package com.grow.cmputf17team4.grow;

/**
 * Class to represent a habit type
 * @author
 */

public class HabitType {
    private int id;
    private String name;
    private String reason;
    private int userId;
    private boolean[] routine;
    private int numCompleted;
    private int numMissed;

    public HabitType(String name) {

        this.name = name;
        this.id = 1;
        this.reason = "";
        this.routine = new boolean[1];
        this.routine[0] = true;
        this.userId = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean[] getRoutine() {
        return routine;
    }

    public void setRoutine(boolean[] routine) {
        this.routine = routine;
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
}