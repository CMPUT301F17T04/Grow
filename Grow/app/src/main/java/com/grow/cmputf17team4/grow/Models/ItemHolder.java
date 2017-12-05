package com.grow.cmputf17team4.grow.Models;

import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Helper class for item
 */
public class ItemHolder{
    private static HashMap<String,Class> map;
    private String type;
    private String json;
    static {
        map  = new HashMap<>();
        map.put(Constant.TYPE_HABIT_EVENT, HabitEvent.class);
        map.put(Constant.TYPE_HABIT_TYPE, HabitType.class);
        map.put(Constant.TYPE_USER,User.class);
        map.put(Constant.TYPE_FOLLOWINGS,IDList.class);
        map.put(Constant.TYPE_REQUESTS,IDList.class);
    }

    ItemHolder(Item item){
        type = item.getType();
        if (type == null){
            throw new Error("Unhandled item type");
        }
        Class t = map.get(type);
        if (t == null){
            throw new Error("Unhandled item class");
        }
        json = new Gson().toJson(t.cast(item));
    }

    public Item getItem(){
        Class t = map.get(type);
        if (t == null){
            throw new Error("Unhandled item class "+type);
        }
        return (Item) new Gson().fromJson(json,t);
    }

}
