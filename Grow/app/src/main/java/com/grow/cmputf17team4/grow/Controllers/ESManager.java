package com.grow.cmputf17team4.grow.Controllers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.Item;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

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

    public static class isUIdExist extends AsyncTask<String,Void,Integer>{
        static final int PASS = 1;
        static final int ER_INT = 2;
        static final int HAD= 3;
        @Override
        protected Integer doInBackground(String... strings) {
            Get get = new Get.Builder(Constant.ELASTIC_SEARCH_INDEX,strings[0]).type(Constant.TYPE_USER).build();
            try{
                JestResult result = ourInstance.client.execute(get);
                if (result.isSucceeded()){
                    return HAD;
                } else{
                    return PASS;
                }
            } catch (Exception e){
                e.printStackTrace();
                return ER_INT;
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
}
