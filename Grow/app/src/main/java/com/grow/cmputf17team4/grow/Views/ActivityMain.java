package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.Controllers.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_PICK_IMAGE;
import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_TAKE_PHOTO;


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
    private ViewPagerAdapter viewPagerAdapter;
    private boolean mLocationPermissionGranted = false;
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    /**
     * OnCreate method.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setupBotNav();
        setupViewPager();
        getLocationPermission();
        DataManager.loadFromFile(this);
        DataManager.getInstance().login(this);
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
     * Setupt the view pager for fragment_community
     */
    private void setupViewPager() {
        viewPager = findViewById(R.id.viewpager_main);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPagerAdapter.addFragment(FragmentHabitList.newInstance());
        viewPagerAdapter.addFragment(FragmentEventList.newInstance());
        viewPagerAdapter.addFragment(FragmentCommunity.newInstance());
        viewPagerAdapter.addFragment(FragmentProfile.newInstance());
        viewPager.setAdapter(viewPagerAdapter);

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

                switch (position){
                    case 2:
                        new Cache.FetchTask().execute();
                        break;
                    case 3:
                        ((FragmentProfile)viewPagerAdapter.getItem(position)).refresh();
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setupToolBar(){
        toolBarViews = new SparseArray<>();
        int habit = 0, event =1, community = 2, profile = 3;
        toolBarViews.put(R.id.toolbar_title,Arrays.asList(habit,community,profile));
        toolBarViews.put(R.id.toolbar_btn_add_habit,Arrays.asList(habit));
        toolBarViews.put(R.id.toolbar_btn_filter,Arrays.asList(event));
        toolBarViews.put(R.id.toolbar_btn_my_events_map,Arrays.asList(event));
        toolBarViews.put(R.id.toolbar_btn_friend_events_map,Arrays.asList(community));
        toolBarViews.put(R.id.toolbar_search_event,Arrays.asList(event));
        toolBarViews.put(R.id.toolbar_btn_follow,Arrays.asList(community));



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManager.waitAllTaskDone();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        ((FragmentProfile)viewPagerAdapter.getItem(3)).result(requestCode,data);

    }


    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    Log.d("googlemap","permission got");
                }
            }
        }

    }


}


