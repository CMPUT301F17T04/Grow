package com.grow.cmputf17team4.grow.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.EventListAdapter;
import com.grow.cmputf17team4.grow.Controllers.HabitListAdapter;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.User;

import java.util.ArrayList;

/**
 * Fragment for habit event list
 * @author Yizhou Zhao
 */


public class FragmentEventList extends Fragment {
    EventListAdapter adapter;

    public static FragmentEventList newInstance() {
        Bundle args = new Bundle();
        FragmentEventList fragment = new FragmentEventList();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_card_list, null);
        ListView listView = (ListView) view.findViewById(R.id.card_list_view);
        adapter = new EventListAdapter(getActivity(), DataManager.getInstance().getEventList());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.commit(null,null);
    }
}
