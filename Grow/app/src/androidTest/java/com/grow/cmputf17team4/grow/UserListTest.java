package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Aeolus on 2017-11-12.
 */

public class UserListTest extends ActivityInstrumentationTestCase2 {

    public UserListTest() {
        super(com.grow.cmputf17team4.grow.UserList.class);
    }

    public void testGetSize(){
        UserList userlist = new UserList();
        assertEquals("UserList.getsize error.", userlist.getSize(), 0);
    }

    public void testAddUser(){
        UserList userlist = new UserList();
        User user = new User("FF", 1);

        userlist.addUser(user);
        assertTrue("UserList.adduser error.", userlist.hasUser(user));
    }

    public void testDeleteUser(){
        UserList userlist = new UserList();
        User user = new User("FF", 1);

        userlist.addUser(user);
        userlist.deleteUser(user);
        assertFalse("UserList.deleteuser error.", userlist.hasUser(user));
    }

    public void testhasUser(){
        UserList userlist = new UserList();
        User user = new User("FF", 1);
        User buser = new User("TT", 2);

        userlist.addUser(user);
        assertTrue("UserList.hasuser error.", userlist.hasUser(user));
        assertFalse("UserList.hasuser error.", userlist.hasUser(buser));
    }
}
