package com.grow.cmputf17team4.grow.Controllers;

import java.util.Observable;
import java.util.Observer;


/**
 * Class that deal with elastic search
 * @since 1.0
 * @author Qin Zhang
 */

public class ESManager implements Observer {
    private static final ESManager ourInstance = new ESManager();
    /**
     * Get the ESManager object
     * @return the ESManager object
     */
    public static ESManager getInstance() {
        return ourInstance;
    }

    /**
     * Contructor of the ESManager
     */
    private ESManager() {
    }
    /**
     * Update the data in Elastic Search
     * @param o an Observable
     * @param arg an Object
     */
    @Override
    public void update(Observable o, Object arg) {
        return;
    }
}
