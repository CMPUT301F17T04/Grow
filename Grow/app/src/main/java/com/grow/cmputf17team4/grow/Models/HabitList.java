package com.grow.cmputf17team4.grow.Models;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by chris on 2017/11/7.
 */

public class HabitList extends HashMap<UUID,HabitType>{
    public void add(HabitType habitType){
        put(habitType.getUid(),habitType);
    }
}
