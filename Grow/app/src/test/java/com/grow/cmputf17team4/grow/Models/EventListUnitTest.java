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
 * Created by chris on 2017-12-04.
 */
public class EventListUnitTest{
    private EventList eventList;
    private HabitEvent habitEvent;
    private final String id = Constant.TEST_USER_NAME;
    @Before
    public void setUp(){
        DataManager.prepareForTesting();
    }
    @Test
    public void testAddEvent(){
        eventList = DataManager.getInstance().getEventList();
        int size = eventList.size();
        HabitType habitType = new HabitType(id);
        String habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        DataManager.getInstance().getHabitList().add(habitType);
        eventList.add(habitEvent);
        assertEquals(eventList.size(),size+1);
    }
    @Test
    public void testRemoveEvent(){
        eventList = DataManager.getInstance().getEventList();
        HabitType habitType = new HabitType(id);
        String habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        DataManager.getInstance().getHabitList().add(habitType);
        eventList.add(habitEvent);
        int size = eventList.size();
        eventList.remove(habitEvent.getUid());
        assertEquals(eventList.size(),size-1);
    }
    @Test
    public void testRemoveAll(){
        eventList = DataManager.getInstance().getEventList();
        HabitType habitType = new HabitType(id);
        String habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        HabitEvent habitEvent1 = new HabitEvent(habitTypeID,id);
        DataManager.getInstance().getHabitList().add(habitType);
        eventList.add(habitEvent);
        eventList.add(habitEvent1);
        assertEquals(eventList.size(),2);
        int size = eventList.size();
        eventList.removeAll(habitType);
        assertEquals(eventList.size(),0);
    }
}