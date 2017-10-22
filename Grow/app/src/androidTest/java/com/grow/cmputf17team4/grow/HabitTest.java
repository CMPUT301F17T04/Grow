package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kivrikog on 10/20/17.
 */

public class HabitTest extends ActivityInstrumentationTestCase2{

    public HabitTest {
        super(com.grow.cmputf17team4.grow.HabitActivity.class);
    
    public void testGetName(){
        User auser = new User("pelin");
        assertEquals("User.GetName Error", auser.getName(), "pelin");
    }
    public void testGetComment(){
      	
        Comment ncomment= new Comment("hello")
        assertEquals("Comment.GetComment Error", ncomment.getComment(), "hello");
    }
     public void testSetName(){
        User auser = new User("pelin");
        auser.setName("new_user");
        assertEquals("User.SetName Error", auser.getName(), "new_user");
  		
     }
    public void testSetComment(){
  		
  		Comment ncomment = new Comment("pelin");
        ncomment.setComment("new_user");
        assertEquals("Comment.SetComment Error", ncomment.getComment(), "new_user");
    }
}

