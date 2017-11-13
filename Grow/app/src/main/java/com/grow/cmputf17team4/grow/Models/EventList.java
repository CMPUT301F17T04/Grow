package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

import static com.grow.cmputf17team4.grow.R.drawable.event;

/**
 * Created by chris on 2017/11/8.
 */


// it allows user to add event to the event list
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
