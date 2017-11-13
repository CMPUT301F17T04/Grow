package com.grow.cmputf17team4.grow.Models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Infrared on 2017-11-13.
 */
public class HabitListUnitTest {
    private HabitList habitList;
    private HabitType habitType;
    @Test
    public void testAdd(){
        habitList = new HabitList();
        habitType = new HabitType();
        habitList.add(habitType);
        assertEquals(habitList.size(),1);
    }
    @Test
    public void testSetAndIsChanged(){
        habitList = new HabitList();
        Boolean changed = true;
        habitList.setChanged(changed);
        assertEquals(habitList.isChanged(),true);
    }


}