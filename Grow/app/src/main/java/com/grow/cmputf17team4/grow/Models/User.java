package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.Identifiable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class to represent a user
 * @author
 */

public class User implements Identifiable{
    private UUID uid;

    public User() {
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
}
