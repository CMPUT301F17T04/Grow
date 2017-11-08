package com.grow.cmputf17team4.grow;

import java.util.ArrayList;

/**
 * Created by Aeolus on 2017-11-08.
 */

public class UserList {

    private ArrayList<User> userList = new ArrayList<>();

    public UserList(){}

    public int getSize(){
        return userList.size();
    }

    public void addUser(User Newuser){
        userList.add(Newuser);
    }

    public void deleteUser(User user){
        userList.remove(user);
    }

    public boolean hasUser(User user){
        return userList.contains(user);
    }
}
