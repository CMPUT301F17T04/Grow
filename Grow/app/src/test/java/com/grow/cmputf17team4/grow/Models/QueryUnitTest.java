package com.grow.cmputf17team4.grow.Models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Infrared on 2017-11-13.
 */
public class QueryUnitTest {
    @Test
    public void TestQueryRun(){
        Query query = new Query(1,"nini","haha","fufu");
        assertEquals(query.run(),false);

    }

}