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
    /**
     * Check which day is repeated in a week
     * @param i the day to check
     * @return True if repeated on that day, False if not.
     */
    public boolean getRepeat(int i) {
        return repeats[i];
    }
    /**
     * Set the habit to repeat on days in a week
     * @param i the day in a week that will repeat the habit event
     * @param repeat True if to repeat, false if not.
     */
    public void setRepeat(int i,boolean repeat) {
        this.repeats[i] = repeat;
    }
    /**
     * Constructor of the Habit(type)
     */
    public HabitType() {
        uid = generateUid();
        startDate = new Date();
        name = "";
        reason = "";
        record = new ArrayList<>();
    }
    /**
     * get the id of the habit (type)
     * @return the id of the habit (type)
     */
    public UUID getUid() {
        return uid;
    }
    /**
     * Generate the habit event of the habit (type)
     * @return the habit event generated
     */
    public HabitEvent buildEvent(){
        return new HabitEvent(getName());
    }
    /**
     * get the name of the habit (type)
     * @return the name of the habit
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name of the habit name
     * @param name the name that need to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the reason of the habit
     * @return the reason
     */
    public String getReason() {
        return reason;
    }
    /**
     * set the reason for the habit
     * @param reason the reason to be set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * get the start date of the habit (type)
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Set the start date of the habit
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Check if this habit has event on today
     * @return True if yes, False if not
     */
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
    /**
     * Get the next day in a week, that will have habit event for this habit
     * @return the day that will has the next habit event
     */
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
    /**
     * get the record of the habit events
     * @return the arrayList contains the record
     */
    public ArrayList<Date> getRecord() {
        return record;
    }

    /**
     * Compare the next event with the the next event of other habit (type)
     * @param o the habit that will be compare
     * @return 1 if there are difference, 0 if not
     */

    @Override
    public int compareTo(@NonNull HabitType o) {
        return getNextEventDay().compareTo(o.getNextEventDay());
    }
    /**
     * Check if the habit event is done
     * @return True if done, False if not.
     */
    public boolean alreadyDone(){
        int index = Collections.binarySearch(record,Calendar.getInstance().getTime(), new DateComparator());
        return index >= 0;
    }
    /**
     * Generate the id of the habit (type)
     * @return the UUID of the habit
     */
    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }

    /**
     * Get the constant INDEX_HABIT_TYPE
     * @return the constant
     */
    @Override
    public String getIndex() {
        return Constant.INDEX_HABIT_TYPE;
    }

    /**
     * The inner class that helps to compare date.
     */
    private class DateComparator implements Comparator<Date> {
        /**
         * Compare the date
         * @param d1 date 1
         * @param d2 date 2
         * @return 1 if there are difference, 0 if not
         */
        public int compare(Date d1, Date d2) {
            return Constant.TIME_FORMAT.format(d1).compareTo(Constant.TIME_FORMAT.format(d2));
        }
    }
}
