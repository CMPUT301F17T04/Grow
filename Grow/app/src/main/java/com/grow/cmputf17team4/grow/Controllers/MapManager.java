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

    /**
     * Constructor for MapManager
     */
    public MapManager(){
        habitEventList = null;
    }

    /**
     * private instance of MapManager
     */
    private static final MapManager instance = new MapManager();

    /**
     * get private instance of mapManager
     * @return
     */
    public static MapManager getInstance(){
        return instance;
    }

    /**
     * Get habitEvent list
     * @return
     */
    public ArrayList<HabitEvent> getHabitEventList(){
        return habitEventList;
    }

    /**
     * Set habitEvent list
     * @param l
     */
    public void setHabitEventList(ArrayList<HabitEvent> l){
        this.habitEventList = l;
    }
}
