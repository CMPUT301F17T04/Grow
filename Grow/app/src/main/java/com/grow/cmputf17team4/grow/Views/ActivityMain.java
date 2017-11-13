package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.SearchView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.Controllers.ViewPagerAdapter;


/**
 * MainActivity
 * @author Yizhou Zhao
 */
public class ActivityMain extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private User user;
    private String file;
    private BottomNavigationView bottomNavigationView;
    private ListView listView;
    private ImageButton addBtn;
    private Toolbar myToolbar;
    private ActivityMain that;
    private ViewPagerAdapter viewPagerAdapter;
    /**
     * Overrides startActivityForResult.
     * @param intent
     * @param requestCode
     * @param options
     */
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode, options);
    }
    /**
     * OnCreate method.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager.loadFromFile(this);
        that = this;
        addBtn = (ImageButton) findViewById(R.id.toolbar_btn_add_habit);
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        final View title = findViewById(R.id.toolbar_title);
        final ImageButton mapBtn = (ImageButton)findViewById(R.id.toolbar_btn_map);
        final ImageButton filterBtn = (ImageButton)findViewById(R.id.toolbar_btn_filter);
        final SearchView searchView = (SearchView) findViewById(R.id.toolbar_search);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        title.setVisibility(View.GONE);
                        addBtn.setVisibility(View.GONE);
                        mapBtn.setVisibility(View.GONE);
                        filterBtn.setVisibility(View.GONE);
                        searchView.setVisibility(View.GONE);
                        switch (item.getItemId()) {
                            case R.id.navigation_habit:
                                addBtn.setVisibility(View.VISIBLE);
                                title.setVisibility(View.VISIBLE);
                                viewPager.setCurrentItem(0);
                                return false;
                            case R.id.navigation_event:
                                mapBtn.setVisibility(View.VISIBLE);
                                searchView.setVisibility(View.VISIBLE);
                                filterBtn.setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // Initialize Toolbar

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(that, ActivityModifyHabit.class);
                startActivityForResult(intent, Constant.REQUEST_CREATE_HABIT);
            }
        });

        setupViewPager(viewPager);


    }
    /**
     * Setupt the view pager for fragment
     * @param viewPager the view pager that will be set
     */
    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(FragmentHabitList.newInstance());
        viewPagerAdapter.addFragment(FragmentEventList.newInstance());
        viewPagerAdapter.addFragment(FragmentCommunity.newInstance());
        viewPagerAdapter.addFragment(FragmentProfile.newInstance());
        viewPager.setAdapter(viewPagerAdapter);
    }
    /**
     * Called when the the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        DataManager.saveInFile(this);
    }

}
