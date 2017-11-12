package com.grow.cmputf17team4.grow.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.User;

/**
 * Created by Infrared on 2017-11-11.
 */

public class QueryQueueTest extends ActivityInstrumentationTestCase2 {
    public QueryQueueTest() {
        super(QueryQueue.class);
    }

    public void testGetUid(){
        User auser = new User("Fufu");
        assertEquals("QueryQueue.GetUid Error", auser.getUid(), 1);

    }
    public void testGetIndex(){
        User auser = new User("Fufu");
        assertEquals("QueryQueue.GetIndex Error", auser.getIndex(), "Fufu");

    }
    public void testUpdate(){
        Gson gson=new Gson();
        Identifiable ide = new Identifiable();
        assertEquals("QueryQueue.update Error", ide.getIndex(),ide.getUid(),"Fufu",1);
    }
}
