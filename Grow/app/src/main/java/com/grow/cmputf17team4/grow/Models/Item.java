package com.grow.cmputf17team4.grow.Models;

import java.util.UUID;

import io.searchbox.annotations.JestId;

/**
 * Class represent Item in elasticSearch.
 */
public class Item {
    protected String uid;
    protected String type = null;

    public String getUid(){return this.uid;}

    public String generateUid() {
        return UUID.randomUUID().toString();
    }
    public String getType(){return this.type;};
}
