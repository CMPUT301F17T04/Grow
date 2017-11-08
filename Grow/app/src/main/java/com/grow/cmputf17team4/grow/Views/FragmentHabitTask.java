package com.grow.cmputf17team4.grow.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.HabitListAdapter;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.R;

/**
 * Shows the 1st tab in HabitEvent Activity
 * @author Yizhou Zhao
 */


public class FragmentHabitTask extends Fragment{
    private static Context context;
    private HabitListAdapter adapter;
    private ListView listView;

    public static FragmentHabitTask newInstance(Context activity) {
        Bundle args = new Bundle();
        context = activity;
        FragmentHabitTask fragment = new FragmentHabitTask();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_task, null);
        listView = (ListView) view.findViewById(R.id.habit_task_listview);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new HabitListAdapter(context, DataManager.getInstance().getHabitList());
        listView.setAdapter(adapter);
    }
}
