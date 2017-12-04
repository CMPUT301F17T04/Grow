package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Models.App;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.IDList;
import com.grow.cmputf17team4.grow.Models.ItemList;
import com.grow.cmputf17team4.grow.Models.Buffer;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static com.grow.cmputf17team4.grow.Models.Constant.POLLING_INTERVAL;

/**
 * Class that store and load data
 * @since 1.0
 * @author Qin Zhang
 */
public class DataManager {
    private HabitList habitList;
    private Buffer buffer;
    private EventList eventList;
    private User user;
    private static LinkedList<AsyncTask> taskPool = new LinkedList<>();

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
        buffer = new Buffer();
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
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (ourInstance.getUser() != null) {
                    ourInstance.buffer.process();
                    Cache.checkUpdates();
                }
            }
        },0,POLLING_INTERVAL);

    }

    /**
     * Save the change of of user/habit/habitEvent information.
     * Or to create one if not exist.
     * @param context the current context (activity)
     */

    private static void saveInFile(Context context) {
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
    public Buffer getBuffer() {
        return buffer;
    }

    public void login(final Activity activity){
        if (this.user != null){
            return ;
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setPositiveButton("Confirm", null)
                .setView(view)
                .setTitle("Who are you ?")
                .setCancelable(false)
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.dialog_edit_id);
                String name = editText.getText().toString().trim();
                if (name.length() == 0){
                    editText.setError("Name cannot be empty");
                } else {
                    try{
                        int result = new ESManager.CheckExistTask().execute(name).get();
                        switch (result){
                            case Constant.TASK_EXCEPTION:
                                editText.setError(activity.getString(R.string.internet_error));
                                break;
                            case Constant.TASK_SUCCESS:
                                editText.setError("Name already been used");
                                break;
                            case Constant.TASK_FAIL:
                                user = new User(name);
                                buffer.update(Constant.QUERY_CREATE,user);
                                buffer.update(Constant.QUERY_CREATE,new IDList(user.getUid(),Constant.TYPE_REQUESTS));
                                buffer.update(Constant.QUERY_CREATE,new IDList(user.getUid(),Constant.TYPE_FOLLOWINGS));
                                dialog.dismiss();
                                break;
                            default:
                                throw new Error("Unhandled Task Result");

                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private static class SaveLocalDataTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            saveInFile(App.getContext());
            return null;
        }
    }

    public static void waitAllTaskDone(){
        while (!taskPool.isEmpty()){
            if (taskPool.peekFirst().getStatus() == AsyncTask.Status.FINISHED){
                taskPool.removeFirst();
            }
        }
    }

    public static AsyncTask<Void, Void, Void> save(){
        SaveLocalDataTask task =  new SaveLocalDataTask();
        taskPool.add(task);
        task.execute();
        return task;
    }

    public User getUser() {
        return user;
    }

    public static void clear(){
        App.getContext().deleteFile(Constant.FILE_NAME);
        if (getInstance().getUser()!= null) {
            try {
                new CancelAccountTask().execute().get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ourInstance = new DataManager();
    }

    public static class CancelAccountTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            ESManager.delete(ourInstance.getUser());
            return null;
        }
    }
}
