package com.grow.cmputf17team4.grow.Models;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Infrared on 2017-11-13.
 */
public class QueryQueueUnitTest {
    private HabitEvent event;

    @Before
    public void setUp(){
        DataManager.prepareForTest();
        HabitType habitType = new HabitType();
        habitType.setName("Test Type");
        event = habitType.buildEvent();
        event.setComment("Test Comment");
        event.setEncodedImage("Test Image String");
        DataManager.getInstance().getEventList().add(event);
    }
    @Test
    public void TestQueryQueueCreateQuery(){
        assert(DataManager.getInstance().getQueryQueue().size()==1);
        Query query = DataManager.getInstance().getQueryQueue().peek();
        assertEquals(query.getType(),Constant.QUERY_CREATE);
        assertEquals(query.getIndex(),event.getIndex());
        assertEquals(query.getId(),event.getUid().toString());

        HabitEvent decodedEvent = new Gson().fromJson(query.getContent(),HabitEvent.class);
        assertEquals(Constant.TIME_FORMAT.format(event.getDate()),
                Constant.TIME_FORMAT.format(decodedEvent.getDate()));
        assertEquals(decodedEvent.getEncodedImage(),"Test Image String");
        assertEquals(decodedEvent.getUid(),event.getUid());
        assertEquals(decodedEvent.getComment(),"Test Comment");
        assertEquals(decodedEvent.getName(),"Test Type");
    }

    @Test
    public void TestQueryQueueUpdateQuery(){
        event.setComment("Changed Comment");
        DataManager.getInstance().getEventList().commit(event.getUid());
        assert(DataManager.getInstance().getQueryQueue().size()==2);
        Query query = DataManager.getInstance().getQueryQueue().remove();
        assertEquals(query.getType(),Constant.QUERY_CREATE);
        query = DataManager.getInstance().getQueryQueue().remove();
        assertEquals(query.getType(),Constant.QUERY_UPDATE);
        HabitEvent decodedEvent = new Gson().fromJson(query.getContent(),HabitEvent.class);
        assertEquals(decodedEvent.getComment(),"Changed Comment");
    }

    @Test
    public void TestQueryQueueDeleteQuery(){
        DataManager.getInstance().getEventList().remove(event.getUid());
        assert(DataManager.getInstance().getQueryQueue().size()==2);
        Query query = DataManager.getInstance().getQueryQueue().remove();
        assertEquals(query.getType(),Constant.QUERY_CREATE);
        query = DataManager.getInstance().getQueryQueue().remove();
        assertEquals(query.getType(),Constant.QUERY_DELETE);
        assertEquals(query.getId(),event.getUid().toString());
    }


}