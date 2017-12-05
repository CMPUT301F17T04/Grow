package com.grow.cmputf17team4.grow.Views;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grow.cmputf17team4.grow.Controllers.ImageManager;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.R;


import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.w3c.dom.Text;

public class ActivityFollowingHabits extends AppCompatActivity implements OnMapReadyCallback{
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private ImageView iUserImage;
    private TextView tvUserName;

    private CardView cMostRecentEvent;
    private TextView tvHabitName;
    private TextView tvReason;
    private TextView tvHabitStartDate;
    private TextView tvHabitAchieved;

    private CardView cLocation;
    private ImageView iEventImage;
    private TextView tvEventDate;
    private TextView tvEventComment;

    private GoogleMap mMap;
    private MapFragment mapFragment;
    private Location eventLocation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_habits);

        int index = getIntent().getIntExtra(Constant.EXTRA_INDEX,-1);
        HabitType habitType = Cache.getInstance().getHabitTypes().get(index);
        HabitEvent mostRecentEvent = habitType.getMostRecentEvent();


        // initialize UI components
        iUserImage = (ImageView)findViewById(R.id.community_detail_user_image);
        tvUserName = (TextView)findViewById(R.id.community_detail_user_name);

        cMostRecentEvent = (CardView)findViewById(R.id.community_detail_event_card);
        tvHabitName = (TextView)findViewById(R.id.community_detail_text_habit_name);
        tvReason = (TextView)findViewById(R.id.community_detail_text_habit_reason);
        tvHabitStartDate = (TextView)findViewById(R.id.community_detail_text_habit_date);
        tvHabitAchieved = (TextView)findViewById(R.id.community_detail_text_habit_completes);

        cLocation = (CardView)findViewById(R.id.community_detail_map_card);
        iEventImage = (ImageView)findViewById(R.id.community_detail_event_image);
        tvEventDate = (TextView)findViewById(R.id.community_detail_event_date);
        tvEventComment = (TextView)findViewById(R.id.community_detail_comment);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.community_detail_fragment_map);
        mapFragment.getMapAsync(this);


        // if the mostRecentEvent == null, hide corresponding cardViews
        if(mostRecentEvent == null){
            cMostRecentEvent.setVisibility(View.GONE);
            cLocation.setVisibility(View.GONE);
        }

        // Get required data
        String userName = habitType.getUserId();
        String habitName = habitType.getName();
        String reason = habitType.getReason();
        String habitStartDate = habitType.getStringStartDate();
        String achievedEvent = Integer.toString(habitType.getNumCompleted());

        // update UI component to show correct information
        ImageManager imageManager = new ImageManager(new User(userName));
        imageManager.setPic(iUserImage);
        tvUserName.setText(userName);

        tvHabitName.setText(habitName);
        tvHabitStartDate.setText(habitStartDate);
        tvHabitAchieved.setText(achievedEvent);
        tvReason.setText(reason);


        if(mostRecentEvent != null){
            String eventTime = Constant.TIME_FORMAT.format(mostRecentEvent.getDate());
            String eventComment = mostRecentEvent.getComment();
            ImageManager imageManager1 = new ImageManager(mostRecentEvent);
            imageManager1.setPic(iEventImage);
            // update UI component to show correct information
            tvEventDate.setText(eventTime);
            tvEventComment.setText(eventComment);

            eventLocation = mostRecentEvent.getHabitLocation();
            // hide location card view if the location is null
            if(eventLocation == null){
                cLocation.setVisibility(View.GONE);
            }

        }










    }


    @Override
    public void onMapReady(GoogleMap mMap){
        if(eventLocation != null){
            double lat = eventLocation.getLatitude();
            double lng = eventLocation.getLongitude();
            LatLng position = new LatLng(lat,lng);
            mMap.addMarker(new MarkerOptions()
                    .position(position));
        }
    }
}
