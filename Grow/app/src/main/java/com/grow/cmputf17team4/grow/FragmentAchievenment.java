package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Shows the 2nd tab in HabitEvent Activity
 * Allow user to modify a single habit event, also shows the statistics of the type of habit
 */

public class FragmentAchievenment extends Fragment implements View.OnClickListener{


    private EditText comment;
    private TextView numFinished;
    private TextView numMissed;
    private TextView finsihRate;
    private TextView message;
    private TextView duration;
    private TextView startTime;
    private TextView endTime;
    private TextView averageTime;
    private ProgressBar toNextBadge;
    private Switch attachLocation;
    private ImageView attachedPhoto;


    public static FragmentEventList newInstance(String info) {
        Bundle args = new Bundle();
        FragmentEventList fragment = new FragmentEventList();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        tvInfo.setText(getArguments().getString("info"));

        return view;
    }

    public void updateProgressBar(){

    }

    @Override
    public void onClick(View view){

    }
}
