package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/8.
 */

public class EventList extends HashMap<UUID,HabitEvent> {
    public void add(HabitEvent event){
        event.addObserver(DataManager.getInstance().getQueryQueue());
        event.notifyObservers(Constant.QUERY_CREATE);
        put(event.getUid(),event);
    }

    @Override
    public HabitEvent remove(Object key) {
        get(key).notifyObservers(Constant.QUERY_DELETE);
        return super.remove(key);
    }

    public void commit(UUID key){
        get(key).notifyObservers(Constant.QUERY_UPDATE);
    }
}
