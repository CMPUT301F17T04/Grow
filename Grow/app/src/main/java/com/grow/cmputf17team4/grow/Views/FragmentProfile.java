package com.grow.cmputf17team4.grow.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.R;

/**
 * Fragment shows the second tab in the main activity.
 * Shows the map.
 * @author Yizhou Zhao
 */
public class FragmentProfile extends Fragment {

    private EditText userName;
    private TextView userId;
    private ImageButton profilePicture;
    private TextView completed;
    private TextView missed;

    public static FragmentProfile newInstance() {
        Bundle args = new Bundle();
        FragmentProfile fragment = new FragmentProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        return view;
    }

    
}
