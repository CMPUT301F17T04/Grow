package com.grow.cmputf17team4.grow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.grow.cmputf17team4.grow.Views.FragmentEventList;

/**
 * Fragment shows the second tab in the main activity.
 * Shows the map.
 * @author Yizhou Zhao
 */
public class FragmentTrace extends Fragment implements View.OnClickListener{

    private MapView map;
    private double[][] coordinates;
    private Button userSelected;
    private Button habitSelected;
    private User thisUser;
    private Switch fiveKm;
    private int radius;

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
        View view = inflater.inflate(R.layout.fragment_event_list, null);


        return view;
    }

    @Override
    public void onClick(View view){

    }

    /**
     * When the button is clicked, this will update the coordinate array that will shows on the map
     */
    public void updateCoordinate(){

    }

    /**
     * This will update the coordinate shows on the map
     */
    public void updateMap(int radius){

    }
}
