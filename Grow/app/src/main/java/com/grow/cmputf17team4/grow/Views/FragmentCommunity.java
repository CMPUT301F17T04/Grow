package com.grow.cmputf17team4.grow.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Models.User;

import java.util.ArrayList;

/**
 * Fragment shows the 3rd tab in the main activity
 * @author Yizhou Zhao
 */
public class FragmentCommunity extends Fragment {

    private ListView list;
    private EditText searchBox;
    private ArrayList<User> userToDisplay;


    public static FragmentCommunity newInstance() {
        Bundle args = new Bundle();
        FragmentCommunity fragment = new FragmentCommunity();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, null);
        return view;
    }


}
