package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.HabitListAdapter;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.R;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_CREATE_HABIT;

/**
 * Shows the 1st tab in HabitEvent Activity
 * @author Yizhou Zhao
 */


public class FragmentHabitList extends Fragment{
    HabitListAdapter adapter;
    /**
     * Test method to test if the view pager worked properly
     * @return
     */
    public static FragmentHabitList newInstance() {
        Bundle args = new Bundle();
        FragmentHabitList fragment = new FragmentHabitList();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Called when the fragment is created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_card_list, null);
        ListView listView = (ListView) view.findViewById(R.id.card_list_view);
        adapter = new HabitListAdapter(getActivity(), DataManager.getInstance().getHabitList());
        listView.setAdapter(adapter);
        adapter.commit();
        getActivity().findViewById(R.id.toolbar_btn_add_habit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityModifyHabit.class);
                int requestCode = REQUEST_CREATE_HABIT;
                intent.putExtra("requestCode", requestCode);
                startActivityForResult(intent, requestCode);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.commit();
    }
}
