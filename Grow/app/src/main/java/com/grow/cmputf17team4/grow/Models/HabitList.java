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
    /**
     * Set the status indicator to be "changed".
     * @param changed a boolean represents status
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    /**
     * Add habitType to habitlist
     * @param habitType a habitType to be inserted into habitlist
     */
    public void add(HabitType habitType){
        put(habitType.getUid(),habitType);
    }
}
