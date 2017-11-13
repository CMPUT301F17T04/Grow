package com.grow.cmputf17team4.grow.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.EventListAdapter;
import com.grow.cmputf17team4.grow.Controllers.HabitListAdapter;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.User;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Fragment for habit event list
 * @author Yizhou Zhao
 */


public class FragmentEventList extends Fragment {
    private EventListAdapter adapter;
    private String keyword;
    private Set<UUID> showTypes;

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
        final ListView listView = (ListView) view.findViewById(R.id.card_list_view);
        adapter = new EventListAdapter(getActivity(), DataManager.getInstance().getEventList());
        showTypes = DataManager.getInstance().getHabitList().keySet();
        listView.setAdapter(adapter);
        final SearchView searchView = (SearchView) getActivity().findViewById(R.id.toolbar_search);
        keyword = searchView.getQuery().toString();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyword = query;
                adapter.commit(showTypes,keyword);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                keyword =newText;
                adapter.commit(showTypes,keyword);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.commit(showTypes,keyword);
    }
}
