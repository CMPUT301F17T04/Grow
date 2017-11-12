package com.grow.cmputf17team4.grow.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.User;

/**
 * Created by Infrared on 2017-11-11.
 */

public class IdentifiableTest extends ActivityInstrumentationTestCase2 {
    public IdentifiableTest(){
        super(Identifiable.class);
    }
    public void testGetUid(){
        User auser = new User("Fufu");
        assertEquals("Identifiable.GetUid Error", auser.getUid(), 1);

    }
    public void testGenerateUid(){
        User auser = new User("Fufu");
        assertEquals("Identifiable.GenerateUid Error", auser.generateUid(), 1);

    }
    public void testgetIndex(){
        User auser = new User("Fufu");
        assertEquals("Identifiable.GetIndex Error", auser.getIndex(), "Fufu");

    }
}
