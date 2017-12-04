package com.grow.cmputf17team4.grow.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Interface contains all the constant that will be used
 * @since 1.0
 */

public interface Constant {
    int REQUEST_NONE = 0;
    int REQUEST_CREATE_HABIT = 1;
    int REQUEST_MODIFY_HABIT = 2;
    int REQUEST_TAKE_PHOTO = 3;
    int REQUEST_COMPLETE_EVENT = 4;
    int REQUEST_PICK_IMAGE = 5;
    int REQUEST_MODIFY_EVENT = 6;

    int QUERY_CREATE = 0;
    int QUERY_UPDATE = 1;
    int QUERY_DELETE = 2;

    int TASK_SUCCESS = 1;
    int TASK_FAIL = 2;
    int TASK_EXCEPTION = 3;

    int MAX_IMAGE_SIZE = 65536;

    long POLLING_INTERVAL = 3000;

    String FILE_NAME = "grow.dat";
    String ELASTIC_SEARCH_SERVER = "http://cmput301.softwareprocess.es:8080";
    //String ELASTIC_SEARCH_SERVER = "https://5b3c205796b755b5db6f9b28b41fa441.us-east-1.aws.found.io:9243/";
    String ELASTIC_SEARCH_INDEX = "cmput301f17t4";

    String TYPE_HABIT_TYPE = "habitType";
    String TYPE_HABIT_EVENT = "habitEvent";
    String TYPE_USER = "user";
    String TYPE_FOLLOWINGS = "followings";
    String TYPE_REQUESTS = "requests";


    String TEST_USER_NAME = "testUser";



    String EXTRA_ID = "id";
    String EXTRA_INDEX = "index";
    SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("EEEE, MMM dd");

    int[] days ={
        Calendar.SUNDAY,Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,
                Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY};
}
