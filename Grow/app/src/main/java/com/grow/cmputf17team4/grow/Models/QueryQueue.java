package com.grow.cmputf17team4.grow.Models;

import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class that represents the queue, stores the query
 */
public class QueryQueue extends ConcurrentLinkedQueue<Query>{
    /**
     * Update the data in elastic search
     * @param o the object that will be updated
     * @param queryType the query
     */
    public void update(Object o, int queryType) {
        Identifiable i = ((Identifiable) o);
        add(new Query(queryType,
                i.getIndex(),
                i.getUid().toString(),
                i.jsonify()));
    }
}
