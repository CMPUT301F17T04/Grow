package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Views.FragmentEventList;

import java.util.ArrayList;

/**
 * Fragment shows the 3rd tab in the main activity
 * @author Yizhou Zhao
 */
public class FragmentCommunity extends android.support.v4.app.Fragment{

    private ListView list;
    private EditText searchBox;
    private ArrayList<User> userToDisplay;


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
        View view = inflater.inflate(R.layout.fragment_community, null);

        return view;
    }


    public ArrayList<User> getSearchResult(){
        ArrayList<User> result = new ArrayList<User>();
        return result;
    }

    /**
     * This function is called when the user to show in list is updated.
     */
    public void updateUserToShow(){

    }

}
