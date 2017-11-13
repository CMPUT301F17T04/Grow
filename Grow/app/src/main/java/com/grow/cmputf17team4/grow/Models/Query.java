package com.grow.cmputf17team4.grow.Models;

/**
 * Class represent a query
 * @author Qin Zhang
 * @since 1.0
 */
public class Query {
    private int type;
    private String id;
    private String index;
    private String content;
    /**
     * The constructor of the query
     * @param type the type of that will be updated
     * @param index the index
     * @param id the id that will be updated
     * @param content the update
     */
    public Query(int type,String index, String id,  String content) {
        this.type = type;
        this.id = id;
        this.index = index;
        this.content = content;
    }
    /**
     * Determine if the query is running or not
     * @return always false
     */
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
