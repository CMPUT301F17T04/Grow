package com.grow.cmputf17team4.grow.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;

import org.w3c.dom.Text;

public class ActivityFollowingHabits extends AppCompatActivity {

    private ImageView iUserImage;
    private TextView tvUserName;

    private TextView tvHabitName;
    private TextView tvReason;
    private TextView tvHabitStartDate;
    private TextView tvHabitAchieved;

    private TextView tvEventDate;
    private TextView tvEventComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_habits);

        int index = getIntent().getIntExtra(Constant.EXTRA_INDEX,-1);
        HabitType habitType = Cache.getInstance().getHabitTypes().get(index);

        /*
        // initialize UI components
        iUserImage = (ImageView)findViewById(R.id.other_user_picture);
        tvHabitName = (TextView)findViewById(R.id.other_user_habit_name);
        tvReason = (TextView)findViewById(R.id.other_user_reason);
        tvHabitStartDate = (TextView)findViewById(R.id.other_user_start_date);
        tvHabitAchieved = (TextView)findViewById(R.id.other_user_total_achieve);
        tvEventDate = (TextView)findViewById(R.id.other_user_event_date);
        tvEventComment = (TextView)findViewById(R.id.other_user_event_comment);

        // Need a HabitType object and HabitEvent object here.
        HabitType habitType = new HabitType("Super Jack");
        habitType.setRepeat(1,true);
        habitType.setName("Eat chicken");
        habitType.setReason("Hungry");

        HabitEvent mostRecentEvent = new HabitEvent("Eat Chicken","Super Jack");
        mostRecentEvent.setComment("Chicken is good");


        // Since we can't get User object, we set iuserImage to be invisible
        iUserImage.setVisibility(View.INVISIBLE);

        // Get required data
        String userName = habitType.getUserId();
        String habitName = habitType.getName();
        String reason = habitType.getReason();
        String habitStartDate = habitType.getStringStartDate();
        String achievedEvent = Integer.toString(habitType.getNumComplete());
        String eventTime = mostRecentEvent.getStringDate();
        String eventComment = mostRecentEvent.getComment();
*/
    }
}
