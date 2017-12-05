package com.grow.cmputf17team4.grow.Controllers;

import com.grow.cmputf17team4.grow.Models.HabitEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Map Manager
 * @author Yizhou Zhao
 */

public class MapManager {
    /**
     * The core data of MapManager
     */
    private ArrayList<HabitEvent> habitEventList;

    public MapManager(ArrayList<HabitEvent> list){
        habitEventList = list;
    }

    public MapManager(){
        habitEventList = null;
    }

    private static final MapManager instance = new MapManager();

    public static MapManager getInstance(){
        return instance;
    }

    public ArrayList<HabitEvent> getHabitEventList(){
        return habitEventList;
    }

    public void setHabitEventList(ArrayList<HabitEvent> l){
        this.habitEventList = l;
    }
}
