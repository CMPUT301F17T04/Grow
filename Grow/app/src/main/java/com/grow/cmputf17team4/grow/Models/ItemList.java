package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/30.
 * List to stored items with UUID
 */

public class ItemList<T> extends HashMap<String,T> {
    public void add(T t){
        DataManager.getInstance().getBuffer().update(Constant.QUERY_CREATE,t);
        DataManager.save();
        put(((Item)t).getUid(),t);
    }



    @Override
    public T remove(Object key) {
        DataManager.getInstance().getBuffer().update(Constant.QUERY_DELETE,get(key));
        DataManager.save();
        return super.remove(key);
    }
    /**
     * Notify the DataManager to update the change
     * @param key the key of the habit event that is updated
     */
    public void commit(String key){
        Item i = (Item) get(key);
        DataManager.save();
        DataManager.getInstance().getBuffer().update(Constant.QUERY_UPDATE,i);
    }
}
