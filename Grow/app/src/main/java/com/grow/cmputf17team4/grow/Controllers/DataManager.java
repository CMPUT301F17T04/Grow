package com.grow.cmputf17team4.grow.Controllers;

import android.content.Context;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.QueryQueue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Class that store and load data
 * @since 1.0
 * @author Qin Zhang
 */
public class DataManager {
    private HabitList habitList;
    private QueryQueue queryQueue;
    private EventList eventList;

    private static DataManager ourInstance;
    /**
     * Return the DataManager object
     * @return the DataManager object
     */
    public static DataManager getInstance() {
        return ourInstance;
    }

    /**
     * Constructor of the DataManager
     */
    private DataManager() {

        habitList = new HabitList();
        queryQueue = new QueryQueue();
        eventList = new EventList();
    }
    /**
     * Getter of habit events
     * @return A list of habit events of current user
     */
    public EventList getEventList() {
        return eventList;
    }
    /**
     * Read user data from local .json file
     * @param context the current context (activity)
     */
    public static void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(Constant.FILE_NAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            ourInstance = gson.fromJson(in, DataManager.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            ourInstance = new DataManager();
        }

    }

    public static void prepareForTest(){ourInstance = new DataManager();}
    /**
     * Save the change of of user/habit/habitEvent information.
     * Or to create one if not exist.
     * @param context the current context (activity)
     */

    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(Constant.FILE_NAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(ourInstance, writer);
            writer.flush();
            fos.close();
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
    /**
     * Getter of habit
     * @return A list of habit(type) of current user
     */
    public HabitList getHabitList() {
        return habitList;
    }
    /**
     * Getter of Query queue
     * @return A queue of Query that work with elasticSearch
     */
    public QueryQueue getQueryQueue() {
        return queryQueue;
    }
}
