package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

/**
 * Class to represent a habit type
 * @author
 */

public class HabitType implements Comparable<HabitType>,Identifiable {
    private ArrayList<Date> record;
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
        uid = generateUid();
        startDate = new Date();
        name = "";
        reason = "";
        record = new ArrayList<>();
    }

    public UUID getUid() {
        return uid;
    }

    public HabitEvent buildEvent(){
        return new HabitEvent(getName(),getUid());
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
        int result = calender.getTime().compareTo(getStartDate());
        if (result == 0){
            return true;
        } else if (result < 0){
            return false;
        } else {
            int day = calender.get(Calendar.DAY_OF_WEEK);
            return repeats[Arrays.binarySearch(days, day)];
        }
    }

    public Date getNextEventDay(){
        Calendar calendar = Calendar.getInstance();
        int result = calendar.getTime().compareTo(getStartDate());
        if (result < 0){
            calendar.setTime(getStartDate());
        }
        int index = Arrays.binarySearch(days,calendar.get(Calendar.DAY_OF_WEEK));
        for (int i=0;i<7;++i){
            if (repeats[index]){
                break;
            }
            index = (index + 1)%7;
            calendar.add(Calendar.DATE,1);
        }
        calendar.add(Calendar.MINUTE,(int)getName().charAt(0));
        return calendar.getTime();
    }

    public ArrayList<Date> getRecord() {
        return record;
    }

    @Override
    public int compareTo(@NonNull HabitType o) {
        return getNextEventDay().compareTo(o.getNextEventDay());
    }

    public boolean alreadyDone(){
        int index = Collections.binarySearch(record,Calendar.getInstance().getTime(), new DateComparator());
        return index >= 0;
    }

    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }

    @Override
    public String getIndex() {
        return Constant.INDEX_HABIT_TYPE;
    }

    private class DateComparator implements Comparator<Date> {

        public int compare(Date d1, Date d2) {
            return Constant.TIME_FORMAT.format(d1).compareTo(Constant.TIME_FORMAT.format(d2));
        }
    }
}
