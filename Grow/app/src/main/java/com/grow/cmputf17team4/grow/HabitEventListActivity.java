package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class HabitEventListActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_habit_event:
                    Log.d("navigation","habit event");
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_trace:
                    Log.d("navigation","trace");
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_community:
                    Log.d("navigation","community");
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_settings:
                    Log.d("navigation","settings");
                    mViewPager.setCurrentItem(3);
                    return true;
                default:
                    return false;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event_list);

        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onClick(View v){

    }
}
