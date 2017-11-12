package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.HabitEvent;

import java.util.ArrayList;

/**
 * Shows when user is viewing other user's profile
 * @author Yizhou Zhao
 */
public class ActivityOtherUserProfile extends AppCompatActivity implements View.OnClickListener{

    private Button following;
    private boolean isFollowing;
    private User otherUser;
    private int otherUserId;
    private int thisUserId;
    private ArrayList<HabitEvent> otherUserHabits;
    private TextView otherUserName;
    private ListView habitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);
    }

    @Override
    public void onClick(View view){

    }

    /**
     * let this user to follow other user. Add thisUserId into otherUser's Following array
     * @param thisUserId current user's id
     * @param otherUser the object of other user
     */
    public void followUser(int thisUserId, User otherUser){

    }
}
