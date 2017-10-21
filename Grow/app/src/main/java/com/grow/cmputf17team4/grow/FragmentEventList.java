package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Fragment for habit event list
 * @author Yizhou Zhao
 */


public class FragmentEventList extends Fragment {

    private ListView listView;
    private Switch todayOrHistory;
    private ArrayList<HabitEvent> eventsToShow;
    private User thisUser;

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
        View view = inflater.inflate(R.layout.fragment_event_list_today, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        tvInfo.setText(getArguments().getString("info"));

        return view;
    }
}
