package com.grow.cmputf17team4.grow.Models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Infrared on 2017-11-13.
 */

public class EventListUnitTest{
    private EventList eventList;
    private HabitEvent event;

    @Before
    public void setUp(){
        DataManager.prepareForTest();
    }
    @Test
    public void testAddEvent(){
        eventList = DataManager.getInstance().getEventList();
        event = new HabitEvent("fufu");
        eventList.add(event);
        assertEquals(eventList.size(),1);
    }
    @Test
    public void testRemoveEvent(){
        eventList = DataManager.getInstance().getEventList();
        event = new HabitEvent("fufu");
        eventList.add(event);
        eventList.remove(event.getUid());
        assertEquals(eventList.size(),0);
    }
}