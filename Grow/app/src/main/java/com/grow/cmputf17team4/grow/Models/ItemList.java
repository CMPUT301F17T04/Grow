package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/30.
 * List to stored items with UUID
 */

public class ItemList<T> extends HashMap<UUID,T> {
    public void add(T t){
        DataManager.getInstance().getQueryQueue().update(t,Constant.QUERY_CREATE);
        put(((Identifiable)t).getUid(),t);
    }



    @Override
    public T remove(Object key) {
        DataManager.getInstance().getQueryQueue().update(get(key), Constant.QUERY_DELETE);
        return super.remove(key);
    }
    /**
     * Notify the DataManager to update the change
     * @param key the key of the habit event that is updated
     */
    public void commit(UUID key){
        Identifiable i = (Identifiable) get(key);
        DataManager.getInstance().getQueryQueue().update(i,Constant.QUERY_UPDATE);
    }
}
