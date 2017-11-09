package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by chris on 2017/11/8.
 */


// it allows user to add event to the event list
public class EventList extends HashMap<UUID,HabitEvent> {
    public void add(HabitEvent event){
        //add event
        event.addObserver(DataManager.getInstance().getQueryQueue());

        event.notifyObservers(Constant.QUERY_CREATE);

        put(event.getUid(),event);
    }

    @Override
    public HabitEvent remove(Object key) {
        // remove the event
        get(key).notifyObservers(Constant.QUERY_DELETE);
        return super.remove(key);
    }

    public void commit(UUID key){
        // change event/ update event
        get(key).notifyObservers(Constant.QUERY_UPDATE);
    }
}
