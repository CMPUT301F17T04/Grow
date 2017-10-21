package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by kivrikog on 10/20/17.
 */

public class HabitTest extends ActivityInstrumentationTestCase2{

    public HabitTest {
        super(com.grow.cmputf17team4.grow.HabitActivity.class);
    
    public void testGetName(){
        HabitList habits = new HabitList(); //
        Name name = new NewName("getting name");
        habits.add(name);
        Habit returnedName = habits.getName(0);
        assertEquals(returnedName.getName(), name.getName());
        assertFalse(name==null);
    }
    public void testGetComment(){
      	HabitList habits = new HabitList(); //
        Comment comment = new NewComment("getting comment");
        habits.add(comment);
        Habit returnedComment = habits.getComment(0);
        assertEquals(returnedComment.getComment(), comment.getComment());
        assertFalse(comment==null);
    }
     public void testSetName(){
  		
  		HabitList list = new HabitList();
  		Name name = new NewName("set the name");
  		list.add(name);
  		assertTrue(list.setName(name));
  		assertFalse(list.setName(""));
     }
    public void testSetComment(){
  		
  		HabitList list = new HabitList();
  		Comment comment = new NewComment("set the comment");
  		list.add(comment);
  		assertTrue(list.setComment(comment));
  		assertFalse(list.setComment(""));
    }
  }
}
