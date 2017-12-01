package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.Identifiable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class to represent a user
 * @author not yet implement
 */

public class User implements Identifiable{
    private UUID uid;
    private String name;

    public User(String name) {
        this.name = name;
        this.uid = generateUid();
    }

    @Override
    public String getIndex() {
        return Constant.INDEX_USER;
    }

    @Override
    public UUID generateUid() {
        return UUID.randomUUID();
    }

    @Override
    public UUID getUid() {
        return uid;
    }

    @Override
    public String jsonify() {
        return null;
    }
}
