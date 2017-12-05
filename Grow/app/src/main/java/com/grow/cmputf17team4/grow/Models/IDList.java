package com.grow.cmputf17team4.grow.Models;

import java.util.ArrayList;

/**
 * Class stores IDs
 */
public class IDList extends Item {
    private ArrayList<String> payload;
    private boolean isChanged;

    public IDList(String uid,String type){
        this.payload = new ArrayList<>();
        this.uid = uid;
        this.type = type;
        this.isChanged = false;

    }

    public ArrayList<String> getPayload() {
        return payload;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
