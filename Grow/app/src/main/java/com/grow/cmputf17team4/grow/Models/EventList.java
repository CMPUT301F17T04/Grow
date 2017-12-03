package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

/**
 * Created by qin7 on 2017/12/2.
 */

public class EventList extends ItemList<HabitEvent> {
    @Override
    public void add(HabitEvent event) {
        super.add(event);
        HabitType habitType = event.getHabitType();
        if (habitType.getMostRecentEvent() != null){
            habitType.getMostRecentEvent().setNextEvent(event.getUid());
            event.setPrevEvent(habitType.getMostRecentEvent().getUid());
        }
        habitType.setMostRecentEvent(event);
        DataManager.save();
    }

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
        }
        DataManager.save();
        return removed;
    }

    @Override
    public void commit(String key) {
        DataManager.save();
    }

    public void removeAll(HabitType habitType){
        HabitEvent cursor = habitType.getMostRecentEvent();
        while (cursor != null){
            super.remove(cursor.getUid());
            cursor = get(cursor.getPrevEvent());
        }
    }
}
