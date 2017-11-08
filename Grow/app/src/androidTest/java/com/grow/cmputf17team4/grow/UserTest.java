package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;

import java.util.ArrayList;

/**
 * Created by zhai on 10/19/17.
 */

public class UserTest extends ActivityInstrumentationTestCase2{

    public UserTest() {
        super(com.grow.cmputf17team4.grow.User.class);
    }

    public void testGetName() {
        User auser = new User("Fufu");
        assertEquals("User.GetName Error", auser.getName(), "Fufu");
    }

    public void testSetName() {
        User auser = new User("Fufu");
        auser.setName("Dumb");
        assertEquals("User.SetName Error", auser.getName(), "Dumb");
    }

    public void testGetId(){
        User auser = new User("Fufu");
        assertEquals("User.GetId Error", auser.getId(), 1);
    }

    public void testSetId(){
        User auser = new User("Fufu");
        auser.setId(23333);
        assertEquals("User.SetId Error", auser.getId(), 23333);
    }

    public void testGetNumCompleted(){
        User auser = new User("Fufu");
        assertEquals("User.GetNumCompleted Error", auser.getNumCompleted(), 0);
    }

    public void testSetNumCompleted(){
        User auser = new User("Fufu");
        auser.setNumCompleted(10);
        assertEquals("User.SetNumCompleted Error", auser.getNumCompleted(), 10);
    }

    public void testGetNumMissed(){
        User auser = new User("Fufu");
        assertEquals("User.GetNumMissed Error", auser.getNumMissed(), 0);
    }

    public void testSetNumMissed(){
        User auser = new User("Fufu");
        auser.setNumMissed(10);
        assertEquals("User.SetNumMissed Error", auser.getNumMissed(), 10);
    }

    public void testGetHabitTypesList(){
        User auser = new User("Fufu");
        ArrayList<HabitType> newList = new ArrayList<>();
        assertEquals("User.GetHabitTypesList Error", auser.getHabitTypesList(), newList);
    }

    public void testSetHabitTypesList(){

    }

    public void testGetHabitFollowers(){
        User auser = new User("Fufu");
        int[] nofollower = auser.getFollowers();
        assertEquals("User.GetHabitFollowers Error", nofollower[0], 0);
    }

    public void testSetHabitFollowers(){
        User auser = new User("Fufu");
        int[] havefollowers = new int[100];
        havefollowers[0] = 233;
        auser.setFollowers(havefollowers);

        int[] newfollowers = auser.getFollowers();
        assertEquals("User.SetHabitFollowers Error", newfollowers[0], 233);
    }

    public void testGetHabitFollowing(){
        User auser = new User("Fufu");
        int[] nofollowing = auser.getFollowings();
        assertEquals("User.GetHabitFollowing Error", nofollowing[0], 0);
    }

    public void testSetHabitFollowing(){
        User auser = new User("Fufu");
        int[] havefollowings = new int[100];
        havefollowings[0] = 233;
        auser.setFollowings(havefollowings);

        int[] newfollowings = auser.getFollowings();
        assertEquals("User.SetHabitFollowing Error", newfollowings[0], 233);
    }

    public void testGetHabitEventHistory(){
        User auser = new User("Fufu");
        ArrayList<HabitEvent> newList = new ArrayList<>();
        assertEquals("User.GetHabitEventHistory Error", auser.getHabitEventHistory(), newList);
    }

    public void testSetHabitEventHistory(){

    }

    public void testGetHabitEventToday(){
        User auser = new User("Fufu");
        ArrayList<HabitEvent> newList = new ArrayList<>();
        assertEquals("User.GetHabitEventToday Error", auser.getHabitEventToday(), newList);
    }

    public void testSetHabitEventToday(){

    }
}

