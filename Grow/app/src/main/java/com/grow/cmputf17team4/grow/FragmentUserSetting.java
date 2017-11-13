package com.grow.cmputf17team4.grow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Views.FragmentEventList;

/**
 * Fragment shows the second tab in the main activity.
 * Shows the map.
 * @author Yizhou Zhao
 */
public class FragmentUserSetting extends Fragment implements View.OnClickListener{

    private EditText userName;
    private TextView userId;
    private ImageButton profilePicture;
    private TextView completed;
    private TextView missed;

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
        View view = inflater.inflate(R.layout.fragment_user_setting, null);


        return view;
    }

    @Override
    public void onClick(View view){

    }

    
}
