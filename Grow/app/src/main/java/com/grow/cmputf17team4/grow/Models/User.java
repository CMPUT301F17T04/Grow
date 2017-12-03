package com.grow.cmputf17team4.grow.Models;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class to represent a user
 * @author not yet implement
 */

public class User extends Item{
    private ArrayList<String> habitList;

    public User(String uid) {
        this.uid = uid;
        this.type = Constant.TYPE_USER;
        this.habitList = new ArrayList<>();
    }

    public ArrayList<String> getHabitList(){
        return this.habitList;
    }



}
