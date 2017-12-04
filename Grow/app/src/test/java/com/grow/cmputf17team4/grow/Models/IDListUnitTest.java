package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by chris on 2017-12-04.
 */

public class IDListUnitTest {
    private ArrayList<String> payload;
    private boolean isChanged;
    String uid = "fufu";
    String type = "test";
    @Test
    public void testGetPayload(){
        IDList idList = new IDList(uid,type);
        assertNotNull(idList.getPayload());
    }
    @Test
    public void testSetAndIsChanged(){
        IDList idList = new IDList(uid,type);
        assertEquals(idList.isChanged(),false);
        idList.setChanged(true);
        assertEquals(idList.isChanged(),true);
    }
}
