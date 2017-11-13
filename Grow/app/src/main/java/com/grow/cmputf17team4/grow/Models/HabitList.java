package com.grow.cmputf17team4.grow.Models;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/7.
 */

public class HabitList extends HashMap<UUID,HabitType>{
    private boolean changed;

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void add(HabitType habitType){
        put(habitType.getUid(),habitType);
    }
}
