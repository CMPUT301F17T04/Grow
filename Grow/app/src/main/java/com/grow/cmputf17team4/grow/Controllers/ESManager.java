package com.grow.cmputf17team4.grow.Controllers;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by qin7 on 2017/11/7.
 */

public class ESManager implements Observer {
    private static final ESManager ourInstance = new ESManager();

    public static ESManager getInstance() {
        return ourInstance;
    }

    private ESManager() {
    }

    @Override
    public void update(Observable o, Object arg) {
        return;
    }
}
