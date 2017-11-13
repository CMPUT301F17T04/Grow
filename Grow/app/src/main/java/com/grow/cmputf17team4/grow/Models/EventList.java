package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

import static com.grow.cmputf17team4.grow.R.drawable.event;

/**
 * Created by qin7 on 2017/11/8.
 */

public class EventList extends HashMap<UUID,HabitEvent> {
    public void add(HabitEvent event){
        DataManager.getInstance().getQueryQueue().update(event,Constant.QUERY_CREATE);
        put(event.getUid(),event);
    }

    @Override
    public HabitEvent remove(Object key) {
        DataManager.getInstance().getQueryQueue().update(get(key), Constant.QUERY_DELETE);
        return super.remove(key);
    }

    public void commit(UUID key){
        HabitEvent habitEvent = get(key);
        if (habitEvent.isChanged()){
            DataManager.getInstance().getQueryQueue().update(habitEvent,Constant.QUERY_UPDATE);
        }
    }
}
