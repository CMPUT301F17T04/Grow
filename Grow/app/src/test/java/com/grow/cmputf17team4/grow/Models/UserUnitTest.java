package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by chris on 2017-12-04.
 */

public class UserUnitTest {
    private ArrayList<String> habitList;
    private String encodedImage = "image";
    private String date;
    String uid = "fufu";
    String habitId = "test";

    @Before
    public void setUp(){
        DataManager.prepareForTesting();
    }

    @Test
    public void testSetAndGetEncodedImage(){
        User user = new User(uid);
        user.setEncodedImage(encodedImage);
        assertEquals(user.getEncodedImage(),encodedImage);
    }
    @Test
    public void testNewHabit(){
        User user = new User(uid);
        user.newHabit(habitId);
        assertEquals(user.getHabitList().size(),1);
    }
    @Test
    public void testDeleteHabit(){
        User user = new User(uid);
        user.newHabit(habitId);
        assertEquals(user.getHabitList().size(),1);
        user.deleteHabit(habitId);
        assertEquals(user.getHabitList().size(),0);
    }
    @Test
    public void testGetDate(){
        User user = new User(uid);
        assertNotNull(user.getDate());
    }
}
