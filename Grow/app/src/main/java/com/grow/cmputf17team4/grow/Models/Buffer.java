package com.grow.cmputf17team4.grow.Models;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ESManager;

import java.util.concurrent.ConcurrentLinkedQueue;

import static com.grow.cmputf17team4.grow.Models.Constant.QUERY_CREATE;
import static com.grow.cmputf17team4.grow.Models.Constant.QUERY_DELETE;
import static com.grow.cmputf17team4.grow.Models.Constant.QUERY_UPDATE;

/**
 * Class that represents the queue, stores the query
 */
public class Buffer extends ConcurrentLinkedQueue<Pair<Integer,ItemHolder>>{
    /**
     * Update the data in elastic search
     * @param o the object that will be updated
     * @param queryType the query
     */

    public void update(int queryType,Object o){
        Item item = (Item) o;
        item.setUserID(DataManager.getInstance().getUser().getUid());
        add(new Pair<>(queryType,new ItemHolder(item)));
    }

    public void process(){
        Pair<Integer,ItemHolder> pair = peek();
        if (pair == null){
            return;
        }
        boolean result;
        Item item = pair.second.getItem();
        if (pair.first.equals(QUERY_CREATE)) {
            result = ESManager.create(item);
        } else if(pair.first.equals(QUERY_UPDATE)){
            result = ESManager.update(item);
        } else if(pair.first.equals(QUERY_DELETE)){
            result = ESManager.delete(item);
        } else {
            throw new Error("Unhandle query type");
        }

        if (result){
            poll();
            DataManager.save();
            process();
        }
    }

}
