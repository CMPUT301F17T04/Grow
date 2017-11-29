package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.SearchView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.App;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.Controllers.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * MainActivity
 * @author Yizhou Zhao
 */
public class ActivityMain extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private ActivityMain that = this;
    private SparseArray<List<Integer>> toolBarViews;


    /**
     * OnCreate method.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataManager.loadFromFile(this);
        setupToolBar();
        setupBotNav();
        setupViewPager();
    }
    /**
     * Called when the the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        new SaveLocalDataTask().execute();
    }

    private void setupBotNav(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_habit:
                                viewPager.setCurrentItem(0);
                                return false;
                            case R.id.navigation_event:
                                viewPager.setCurrentItem(1);
                                return false;
                            case R.id.navigation_community:
                                viewPager.setCurrentItem(2);
                                return false;
                            case R.id.navigation_settings:
                                viewPager.setCurrentItem(3);
                                return false;
                            default:
                                return false;
                        }
                    }
                });
    }

    /**
     * Setupt the view pager for fragment
     */
    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
                for (int i = 0; i < toolBarViews.size(); ++ i ){
                    if (toolBarViews.valueAt(i).contains(position)){
                        findViewById(toolBarViews.keyAt(i)).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(toolBarViews.keyAt(i)).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPagerAdapter.addFragment(FragmentHabitList.newInstance());
        viewPagerAdapter.addFragment(FragmentEventList.newInstance());
        viewPagerAdapter.addFragment(FragmentCommunity.newInstance());
        viewPagerAdapter.addFragment(FragmentProfile.newInstance());
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void setupToolBar(){
        toolBarViews = new SparseArray<>();
        int habit = 0, event =1, community = 2, profile = 3;
        toolBarViews.put(R.id.toolbar_title,Arrays.asList(habit,profile));
        toolBarViews.put(R.id.toolbar_btn_add_habit,Arrays.asList(habit));
        toolBarViews.put(R.id.toolbar_btn_filter,Arrays.asList(event));
        toolBarViews.put(R.id.toolbar_btn_map,Arrays.asList(event));
        toolBarViews.put(R.id.toolbar_search_event,Arrays.asList(event));



    }

    private class SaveLocalDataTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            DataManager.saveInFile(App.getContext());
            return null;
        }
    }
}


