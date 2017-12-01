package com.grow.cmputf17team4.grow.Models;

import java.util.UUID;

/**
 * Class to represent a user
 * @author not yet implement
 */

public class User extends Item{

    public User(String uid) {
        this.uid = uid;
        this.type = Constant.TYPE_USER;
    }



}
