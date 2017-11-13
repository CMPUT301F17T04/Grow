package com.grow.cmputf17team4.grow.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.HabitListAdapter;
import com.grow.cmputf17team4.grow.R;

/**
 * Shows the 1st tab in HabitEvent Activity
 * @author Yizhou Zhao
 */


public class FragmentHabitList extends Fragment{
    private HabitListAdapter adapter;
    public static FragmentHabitList newInstance() {
        Bundle args = new Bundle();
        FragmentHabitList fragment = new FragmentHabitList();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_card_list, null);
        ListView listView = (ListView) view.findViewById(R.id.card_list_view);
        adapter = new HabitListAdapter(getActivity(), DataManager.getInstance().getHabitList());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.commit();
    }

}
