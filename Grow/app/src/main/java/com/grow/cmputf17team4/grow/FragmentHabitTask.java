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
 * Shows the 1st tab in HabitEvent Activity
 */

public class FragmentHabitTask extends Fragment implements View.OnClickListener{


    private Button start;
    private Button pause;
    private Button stop;
    private TextView textTimeLeft;
    private TextView textTimeCompleted;
    private ProgressBar progress;
    private TextVeiw habitName;
    private TextView reasonToPlay;

    private Date startTime;
    private Date timeLeft;


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

    public Date calculateDifference(){

    }

    @Override
    public void onClick(View view){

    }
}
