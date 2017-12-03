package com.grow.cmputf17team4.grow.Models;

import java.util.ArrayList;

/**
 * Created by qin7 on 2017/12/2.
 */

public class IDList extends Item {
    private ArrayList<String> payload;

    public IDList(String uid,String type){
        this.payload = new ArrayList<>();
        this.uid = uid;
        this.type = type;

    }

    public ArrayList<String> getPayload() {
        return payload;
    }
}
