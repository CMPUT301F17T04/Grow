package com.grow.cmputf17team4.grow.Models;

import android.util.Log;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

/**
 * Class represent a HabitEvet list
 */
public class EventList extends ItemList<HabitEvent> {

    /**
     * Add given HabitEvent to the list stored in DataManager
     * @param event
     */
    @Override
    public void add(HabitEvent event) {
        super.add(event);
        HabitType habitType = event.getHabitType();
        if (habitType.getMostRecentEvent() != null){
            habitType.getMostRecentEvent().setNextEvent(event.getUid());
            event.setPrevEvent(habitType.getMostRecentEvent().getUid());
        }
        habitType.setMostRecentEvent(event);
        habitType.addNumCompleted(1);
        DataManager.getInstance().getHabitList().commit(habitType.getUid());
        DataManager.save();
    }

    /**
     * Remove habitEvent from list according to given key
     * @param key
     * @return
     */
    @Override
    public HabitEvent remove(Object key) {
        HabitEvent removed = super.remove(key);
        HabitType habitType = removed.getHabitType();
        String prevID = removed.getPrevEvent();
        String nextID = removed.getNextEvent();
        if (prevID != null){
            get(prevID).setNextEvent(nextID);
        }
        if (nextID != null){
            get(nextID).setPrevEvent(prevID);
        } else {
            if (prevID == null){
                habitType.setMostRecentEvent(null);
            } else {
                habitType.setMostRecentEvent(get(prevID));
            }
            DataManager.getInstance().getHabitList().commit(habitType.getUid());
        }
        habitType.addNumCompleted(-1);
        DataManager.save();
        return removed;
    }

    /**
     * Commit change
     * @param key
     */
    @Override
    public void commit(String key) {
        if (get(key).getNextEvent() == null) {
            HabitType habitType = get(key).getHabitType();
            habitType.setMostRecentEvent(get(key));
            DataManager.getInstance().getHabitList().commit(habitType.getUid());
        } else {
            DataManager.save();
        }
    }

    /**
     * Clean the habitEvent list
     * @param habitType
     */
    public void removeAll(HabitType habitType){
        HabitEvent cursor = habitType.getMostRecentEvent();
        while (cursor != null){
            super.remove(cursor.getUid());
            cursor = get(cursor.getPrevEvent());
        }
    }
}
