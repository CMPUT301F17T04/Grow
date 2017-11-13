package com.grow.cmputf17team4.grow.Models;

import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by qin7 on 2017/11/8.
 */

public class QueryQueue extends ConcurrentLinkedQueue<Query>{

    public void update(Object o, int queryType) {
        Gson gson = new Gson();
        Identifiable i = ((Identifiable) o);
        add(new Query(queryType,
                i.getIndex(),
                i.getUid().toString(),
                gson.toJson(i)));
    }
}
