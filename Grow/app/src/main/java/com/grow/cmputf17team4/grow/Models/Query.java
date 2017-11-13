package com.grow.cmputf17team4.grow.Models;

/**
 * Created by qin7 on 2017/11/8.
 */

public class Query {
    private int type;
    private String id;
    private String index;
    private String content;

    public Query(int type,String index, String id,  String content) {
        this.type = type;
        this.id = id;
        this.index = index;
        this.content = content;
    }

    public boolean run(){
        return false;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }
}
