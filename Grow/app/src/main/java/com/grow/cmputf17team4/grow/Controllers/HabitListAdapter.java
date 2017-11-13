package com.grow.cmputf17team4.grow.Controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;
import com.grow.cmputf17team4.grow.Views.ActivityModifyHabit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * Adapter for HabitList. HabitList is a list of habit(type)
 * @since 1.0
 * @author Qin Zhang
 */
public class HabitListAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private ArrayList<HabitType> habitList;
    private HabitList modelList;

    /**
     * Initialization of CounterArrayAdapter
     * @param context
     */
    public HabitListAdapter(Context context, HabitList habitMap) {
        this.context = context;
        this.habitList = new ArrayList<HabitType>();
        this.modelList = habitMap;
    }

    /**
     * Initialization of CounterArrayAdapter
     */
    public void commit(){
        habitList.clear();
        for(Map.Entry<UUID,HabitType> entry : modelList.entrySet()) {
            habitList.add(entry.getValue());
        }
        Collections.sort(habitList);
        notifyDataSetChanged();
    }
    /**
     * get the Habit object forom habitList given index
     * @param i the index of the Habit object in HabitList
     * @return A Habit object
     */
    @Override
    public Object getItem(int i) {
        return habitList.get(i);
    }
    /**
     * get the id of the Habit(type) in the HabitList given index i
     * @param i the index of the Habit object in HabitList
     * @return the id of the habit object
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }
    /**
     * get the size of the habitList
     * @return size of the habitList
     */
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
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        /**
         * find separate view of button and textview
         */
        TextView title = (TextView) view.findViewById(R.id.list_item_title);
        TextView date = (TextView) view.findViewById(R.id.list_item_text_subtitle);
        TextView completed = (TextView) view.findViewById(R.id.list_item_text_completed);
        Button btn = (Button) view.findViewById(R.id.list_item_btn_complete);
        view.findViewById(R.id.list_item_image_view).setVisibility(View.GONE);

        final HabitType habitType = (HabitType) getItem(i);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityModifyEvent.class);
                intent.putExtra(Constant.EXTRA_ID,habitType.getUid().toString());
                ((AppCompatActivity)context).startActivityForResult(intent,Constant.REQUEST_COMPLETE_EVENT);
            }
        });

        title.setText(((HabitType)getItem(i)).getName());


        completed.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        if (habitType.hasEventToday()){
            if (habitType.alreadyDone()){
                completed.setVisibility(View.VISIBLE);
            } else {
                btn.setVisibility(View.VISIBLE);
            }
            date.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
            date.setText(context.getString(R.string.next_event,context.getString(R.string.today)));
        } else{
            date.setText(context.getString(R.string.next_event,Constant.TIME_FORMAT.format(habitType.getNextEventDay())));
            date.setTextColor(ContextCompat.getColor(context,R.color.colorTextPrimary));
        }

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * find the ith counter in couters array list
                 */
                Intent intent = new Intent(context,ActivityModifyHabit.class);
                intent.putExtra(Constant.EXTRA_ID,habitType.getUid().toString());
                ((AppCompatActivity)context).startActivityForResult(intent,Constant.REQUEST_MODIFY_HABIT);
            }
        });
        return view;
    }


}
