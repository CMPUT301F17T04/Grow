package com.grow.cmputf17team4.grow;

/**
 * Created by yizho on 2017/10/20.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.HabitEvent;

import java.util.ArrayList;

public class EventTodayAdapter extends ArrayAdapter<HabitEvent>{

    private Context context;
    private int layoutResourceId;
    private ArrayList<HabitEvent> events = new ArrayList<HabitEvent>();

    /**
     * Construct a new CustomAdapter
     * @param context the context of which activity is created
     * @param resourceId the id of xml file that represent each grid
     * @param objects the Arraylist of counters
     */
    public EventTodayAdapter(Context context, int resourceId, ArrayList<HabitEvent> objects){
        super(context, resourceId, objects);
    }

    /**
     * called when the adapter is setting the list item
     * @param position the position of the counter in the arrayList
     * @param convertView
     * @param parent
     * @return the converted view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();

        if(convertView == null){

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    /**
     * Called when updating the Adapter
     * @param newList
     */
    public void updateAdapter(ArrayList<EventTodayAdapter> newList){

    }

    private class ViewHolder{
        TextView textName;
        TextView textReason;
        TextView textCompleted;
    }
}
