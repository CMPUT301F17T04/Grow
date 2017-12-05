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
import java.util.HashMap;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.Update;

/**
 * Class that deal with elasticSearch
 * @author Qin Zhang
 * @author Yizhou Zhao
 */
public class ESManager {
    private static final ESManager ourInstance = new ESManager();
    private JestDroidClient client;

    /**
     * Create a index
     * @param item
     * @return
     */
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

    /**
     * Get object from elasticSearch given id, type and class.
     * @param id
     * @param type
     * @param cls
     * @return
     */
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


    /**
     * Update the given item in server
     * @param item
     * @return true if the update is success, false otherwise
     */
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

    /**
     * Delete the given item
     * @param item
     * @return true if the delete is success, false otherwise
     */
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


    /**
     * Inner class check if the user exit the app
     */
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

    /**
     * Inner class for searching recommended user for current user
     */
    public static class SearchFriendsTask extends AsyncTask<Void,Void,Integer>{
        private ArrayList<User> friends;
        private Runnable preExecute;
        private Runnable postExecuteSuccess;
        private Runnable postExecuteFailed;

        /**
         * Constructor for SearchFriendsTask
         * @param pre
         * @param postSuccess
         * @param postFailed
         */
        public SearchFriendsTask(Runnable pre, Runnable postSuccess, Runnable postFailed){
            friends = new ArrayList<>();
            preExecute = pre;
            postExecuteSuccess = postSuccess;
            postExecuteFailed = postFailed;
        }

        /**
         * Call when the task is being executed.
         * @param voids
         * @return
         */
        @Override
        protected Integer doInBackground(Void... voids) {
            Search search;

            HashMap<String,Integer> count;
            return null;
        }

        /**
         * Get result from search
         * @return
         */
        public ArrayList<User> getFriends() {

            return friends;
        }

        /**
         * Called before executing the task
         */
        @Override
        protected void onPreExecute() {
            // SHOW THE SPInull;NNER WHILE LOADING FEEDS
            preExecute.run();
        }


        /**
         * Called after executing the task
         * @param result
         */
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
