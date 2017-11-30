package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.QueryQueue;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.R;

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
    private User user;

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

    public void login(final Activity activity){
        if (this.user != null){
            return ;
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        final View view = inflater.inflate(R.layout.login, null);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user = new User(((EditText)view.findViewById(R.id.login_edit_id)).getText().toString());
                    }
                })
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
