package com.grow.cmputf17team4.grow.Controllers;

import android.content.Context;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by qin7 on 2017/11/7.
 */

public class DataManager {
    private HabitList habitList;

    private static DataManager ourInstance;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        habitList = new HabitList();
    }


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

    public HabitList getHabitList() {
        return habitList;
    }
}
