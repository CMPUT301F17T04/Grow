package com.grow.cmputf17team4.grow.Controllers;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.IDList;
import com.grow.cmputf17team4.grow.Models.Item;
import com.grow.cmputf17team4.grow.Models.User;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Update;

/**
 * Created by qin7 on 2017/11/30.
 */

public class ESManager {
    private static final ESManager ourInstance = new ESManager();
    private JestDroidClient client;

    public static boolean create(Item item){
        Index index = new Index.Builder(item).id(item.getUid()).index(Constant.ELASTIC_SEARCH_INDEX).type(item.getType()).build();
        try {
            DocumentResult result = ourInstance.client.execute(index);
            if (!result.isSucceeded()){
                Log.i("ESManagerError",result.getErrorMessage() + " on " + item.getType() + ":" + item.getUid());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Object get(String id, String type, Class cls){
        Get get = new Get.Builder(Constant.ELASTIC_SEARCH_INDEX,id).type(type).build();
        try {
            DocumentResult result = ourInstance.client.execute(get);
            if (!result.isSucceeded()){
                Log.i("ESManagerError",result.getErrorMessage());
            }
            return result.getSourceAsObject(cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    public static boolean update(Item item){
        Index update = new Index.Builder(item).id(item.getUid()).index(Constant.ELASTIC_SEARCH_INDEX).type(item.getType()).build();
        try {
            DocumentResult result = ourInstance.client.execute(update);
            if (!result.isSucceeded()){
                Log.i("ESManagerError",result.getErrorMessage() + " on " + item.getType() + ":" + item.getUid());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Item item){
        Delete delete = new Delete.Builder(item.getUid()).index(Constant.ELASTIC_SEARCH_INDEX).type(item.getType()).build();
        try {
            DocumentResult result = ourInstance.client.execute(delete);
            if (!result.isSucceeded()){
                Log.i("ESManagerError",result.getErrorMessage() + " on " + item.getType() + ":" + item.getUid());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static class CheckExistTask extends AsyncTask<String,Void,Integer>{
        @Override
        protected Integer doInBackground(String... strings) {
            Get get = new Get.Builder(Constant.ELASTIC_SEARCH_INDEX,strings[0]).type(Constant.TYPE_USER).build();
            try{
                JestResult result = ourInstance.client.execute(get);
                if (result.isSucceeded()){
                    return Constant.TASK_SUCCESS;
                } else{
                    return Constant.TASK_FAIL;
                }
            } catch (Exception e){
                e.printStackTrace();
                return Constant.TASK_EXCEPTION;
            }

        }
    }

    public static class AppendTask extends AsyncTask<String,Void,Integer>{
        private String type;

        public AppendTask(String type){
            this.type = type;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            Get get = new Get.Builder(Constant.ELASTIC_SEARCH_INDEX,strings[0]).type(this.type).build();
            try{
                JestResult result = ourInstance.client.execute(get);
                if (!result.isSucceeded()){
                    Log.e("ES AppendTask",result.getErrorMessage());
                    return Constant.TASK_FAIL;
                }
                IDList list = result.getSourceAsObject(IDList.class);
                ArrayList<String> payload = list.getPayload();
                String uid = DataManager.getInstance().getUser().getUid();
                if (!payload.contains(uid)){
                    payload.add(uid);
                    list.setChanged(true);
                }
                if (create(list)){
                    return Constant.TASK_SUCCESS;
                } else {
                    return Constant.TASK_FAIL;
                }

            } catch (Exception e){
                e.printStackTrace();
                return Constant.TASK_EXCEPTION;
            }
        }
    }



    private ESManager() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(Constant.ELASTIC_SEARCH_SERVER);
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    public static class SearchFriendsTask extends AsyncTask<Void,Void,Integer>{
        private ArrayList<User> friends;

        private Runnable preExecute;
        private Runnable postExecuteSuccess;
        private Runnable postExecuteFailed;

        public SearchFriendsTask(Runnable pre, Runnable postSuccess, Runnable postFailed){
            friends = new ArrayList<>();
            preExecute = pre;
            postExecuteSuccess = postSuccess;
            postExecuteFailed = postFailed;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
            return Constant.TASK_EXCEPTION;


        }

        public ArrayList<User> getFriends() {
            return friends;
        }

        @Override
        protected void onPreExecute() {
            // SHOW THE SPInull;NNER WHILE LOADING FEEDS
            preExecute.run();
        }


        @Override
        protected void onPostExecute(Integer result) {
            if(result == Constant.TASK_SUCCESS){
                postExecuteSuccess.run();
            }else if(result == Constant.TASK_EXCEPTION){
                postExecuteFailed.run();
            }
        }

    }


}
