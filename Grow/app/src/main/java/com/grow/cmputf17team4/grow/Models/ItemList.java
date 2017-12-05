package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/30.
 * List to stored items with UUID
 */
public abstract class ItemList<T> extends HashMap<String,T> {
    public void add(T t){
        put(((Item)t).getUid(),t);
    }



    @Override
    public T remove(Object key) {
        return super.remove(key);
    }

    public abstract void commit(String key);
}
