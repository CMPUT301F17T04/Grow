package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by yizho on 2017/10/21.
 */

public class FragmentHabitSetting extends Fragment{

    private EditText habitName;
    private EditText reasonToPlay;
    private Button restart;
    private Button delete;
    private RadioGroup routine;

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
        View view = inflater.inflate(R.layout.fragment_habit_settings, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        tvInfo.setText(getArguments().getString("info"));

        return view;
    }

    public void saveInFile(){

    }

    public void inToggle(View view){
        
    }
}
