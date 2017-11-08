package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Class to represent a habit type
 * @author
 */

public class HabitType implements Comparable{
    private UUID uid;
    private String name;
    private String reason;
    private Date startDate;
    private boolean[] repeats = {false,false,false,false,false,false,false};
    private final int[] days = {Calendar.SUNDAY,Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,
        Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY};

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

    public boolean hasEventToday(){
        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_WEEK);
        return repeats[Arrays.binarySearch(days,day)];
    }

    @Override
    public int compareTo(@NonNull Object o) {
       if (hasEventToday()){
            return -1;
        }
        return 0;
    }
}
