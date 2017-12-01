package com.grow.cmputf17team4.grow.Models;

import java.util.UUID;

import io.searchbox.annotations.JestId;

/**
 * Created by qin7 on 2017/12/1.
 */

public class Item {
    protected String uid;
    protected String userID;
    protected String type = null;

    public String getUid(){return this.uid;}

    public String generateUid() {
        return UUID.randomUUID().toString();
    }
    public String getType(){return this.type;};

    public void setUserID(String userID){
        this.userID = userID;
    }
}
