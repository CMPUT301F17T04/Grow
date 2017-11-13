package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

import static com.grow.cmputf17team4.grow.R.drawable.event;

/**
 * Class represents a List contains habit event
 * @author Qin Zhang
 * @since 1.0
 */

public class EventList extends HashMap<UUID,HabitEvent> {
    /**
     * add habit event to event list
     * @param event the event to be added
     */
    public void add(HabitEvent event){
        DataManager.getInstance().getQueryQueue().update(event,Constant.QUERY_CREATE);
        put(event.getUid(),event);
    }
    /**
     * remove habit event from evelt list, given key
     * @param key the key of the habit event that will be removed
     * @return a new list with the given habit event removed
     */
    @Override
    public HabitEvent remove(Object key) {
        DataManager.getInstance().getQueryQueue().update(get(key), Constant.QUERY_DELETE);
        return super.remove(key);
    }
    /**
     * Notify the DataManager to update the change
     * @param key the key of the habit event that is updated
     */
    public void commit(UUID key){
        HabitEvent habitEvent = get(key);
        if (habitEvent.isChanged()){
            DataManager.getInstance().getQueryQueue().update(habitEvent,Constant.QUERY_UPDATE);
        }
    }
}
