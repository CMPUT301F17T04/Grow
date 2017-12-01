package com.grow.cmputf17team4.grow.Models;

import android.util.Log;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by qin7 on 2017/12/1.
 */

public class ItemHolderUnitTest {
    @Test
    public void test(){
        HabitType type = new HabitType();
        type.setName("test");
        Gson gson = new Gson();
        Item item = type;
        String r1 = gson.toJson(item);
        Item item2 = gson.fromJson(r1,Item.class);
        String r2 = gson.toJson(item2);
        assertNotEquals(r1,r2);

        ItemHolder itemHolder = new ItemHolder(item);
        String i1 = gson.toJson(itemHolder);
        itemHolder = gson.fromJson(i1,ItemHolder.class);
        item2 = itemHolder.getItem();
        r2 = gson.toJson(item2);
        assertEquals(r1,r2);

    }
}
