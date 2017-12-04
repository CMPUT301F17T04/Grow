package com.grow.cmputf17team4.grow.Views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.EventListAdapter;
import com.grow.cmputf17team4.grow.Controllers.MapManager;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Fragment for habit event list
 * @author Yizhou Zhao
 */


public class FragmentEventList extends Fragment {
    private EventListAdapter adapter;
    private String keyword;
    private HashMap<String,Boolean> showTypes;
    /**
     * The test function, to test if the view pager works fine
     * @return
     */
    public static FragmentEventList newInstance() {
        Bundle args = new Bundle();
        FragmentEventList fragment = new FragmentEventList();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_card_list, null);
        final ListView listView = (ListView) view.findViewById(R.id.card_list_view);
        adapter = new EventListAdapter(getActivity(), DataManager.getInstance().getEventList());
        showTypes = new HashMap<>();
        listView.setAdapter(adapter);
        final SearchView searchView = (SearchView) getActivity().findViewById(R.id.toolbar_search_event);
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

        ImageButton filter = (ImageButton) getActivity().findViewById(R.id.toolbar_btn_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> keys = showTypes.keySet();
                final String[] names = keys.toArray(new String[keys.size()]);
                final boolean[] checked = new boolean[names.length];
                Arrays.sort(names);

                for (int i = 0; i < names.length; ++ i){
                    checked[i] = showTypes.get(names[i]);
                }
                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Filter Habits")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < checked.length; ++ i){
                                    showTypes.put(names[i],checked[i]);
                                }
                                adapter.commit(showTypes,keyword);
                            }
                        })
                        .setMultiChoiceItems(names, checked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .setCancelable(false)
                        .create();
                dialog.show();
            }
        });

        ImageButton mapView = (ImageButton) getActivity().findViewById(R.id.toolbar_btn_map);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapManager.getInstance().setHabitEventList(adapter.getEventList());
                Intent intent = new Intent(getActivity(), ActivityHabitMap.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showTypes.clear();
        for (Map.Entry<String,HabitEvent> entry : DataManager.getInstance().getEventList().entrySet()){
            showTypes.put(entry.getValue().getName(),true);
        }
        adapter.commit(showTypes,keyword);
    }

}
