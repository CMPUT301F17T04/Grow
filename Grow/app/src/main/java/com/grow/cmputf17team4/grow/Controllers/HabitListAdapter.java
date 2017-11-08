package com.grow.cmputf17team4.grow.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/7.
 */

public class HabitListAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private ArrayList<HabitType> habitList;

    /**
     * Initialization of CounterArrayAdapter
     * @param context
     */
    public HabitListAdapter(Context context, HabitList habitMap) {
        this.context = context;
        this.habitList = new ArrayList<HabitType>();
        for(Map.Entry<UUID,HabitType> entry : habitMap.entrySet()) {
            habitList.add(entry.getValue());
        }
        Collections.sort(habitList);
    }

    @Override
    public Object getItem(int i) {
        return habitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return habitList.size();
    }

    /**
     * edit content in view
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup){
        // get idea from https://stackoverflow.com/questions/17525886/listview-with-add-and-delete-buttons-in-each-row-in-android
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.habit_list_item, null);
        }
        /**
         * find separate view of button and textview
         */
        TextView title = (TextView) view.findViewById(R.id.habit_list_item_title);


        title.setText(((HabitType)getItem(i)).getName());
        return view;
    }
}
