package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

/**
 * Created by qin7 on 2017/12/2.
 */

public class HabitList extends ItemList<HabitType> {
    @Override
    public void add(HabitType habitType) {
        super.add(habitType);
        DataManager.getInstance().getBuffer().update(Constant.QUERY_CREATE,habitType);
        DataManager.getInstance().getUser().newHabit(habitType.getUid());
        DataManager.save();
    }

    @Override
    public HabitType remove(Object key) {
        HabitType removed = super.remove(key);
        DataManager.getInstance().getEventList().removeAll(removed);
        DataManager.getInstance().getBuffer().update(Constant.QUERY_DELETE,removed);
        DataManager.getInstance().getUser().deleteHabit(key.toString());
        DataManager.save();
        return  removed;
    }


    /**
     * Notify the DataManager to update the change
     * @param key the key of the habit event that is updated
     */

    @Override
    public void commit(String key){
        DataManager.getInstance().getBuffer().update(Constant.QUERY_UPDATE,get(key));
        DataManager.save();
    }
}
