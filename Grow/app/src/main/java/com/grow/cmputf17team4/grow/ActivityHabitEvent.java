package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Activity to represent each habit event
 */
public class ActivityHabitEvent extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private User user;
    private String file;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event);

    }





    public void saveInFile(){

    }

    public void loadFromFile(){

    }
}
