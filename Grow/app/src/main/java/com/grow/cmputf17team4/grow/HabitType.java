package com.grow.cmputf17team4.grow;

import java.util.Date;
import java.util.UUID;

/**
 * Class to represent a habit type
 * @author
 */

public class HabitType {
    private UUID uid;
    private String name;
    private String reason;
    private Date startDate;
    private boolean[] repeats = {false,false,false,false,false,false,false};

    public boolean getRepeat(int i) {
        return repeats[i];
    }

    public void setRepeat(int i,boolean repeat) {
        this.repeats[i] = repeat;
    }

    public HabitType() {
        uid = UUID.randomUUID();
        startDate = new Date();
        name = "";
        reason = "";

    }

    public UUID getUid() {
        return uid;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
