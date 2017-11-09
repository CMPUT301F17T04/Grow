package com.grow.cmputf17team4.grow.Models;

import java.text.SimpleDateFormat;

/**
 * Created by qin7 on 2017/11/6.
 */

public interface Constant {
    int REQUEST_NONE = 0;
    int REQUEST_CREATE_HABIT = 1;
    int REQUEST_MODIFY_HABIT = 2;
    int REQUEST_TAKE_PHOTO = 3;
    int REQUEST_COMPLETE_EVENT = 4;
    int REQUEST_PICK_IMAGE = 5;

    int QUERY_CREATE = 0;
    int QUERY_UPDATE = 1;
    int QUERY_DELETE = 2;

    long POLLING_INTERVAL = 3000;

    String FILE_NAME = "grow.dat";
    String ELASTIC_SEARCH_PATH = "http://cmput301.softwareprocess.es:8080";

    String INDEX_HABIT_TYPE = "habitType";
    String INDEX_HABIT_EVENT = "habitEvent";
    SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}
