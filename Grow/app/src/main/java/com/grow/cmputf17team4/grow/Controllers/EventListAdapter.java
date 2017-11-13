package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;
import com.grow.cmputf17team4.grow.Views.ActivityModifyHabit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by qin7 on 2017/11/12.
 */

public class EventListAdapter extends BaseAdapter implements ListAdapter {
    private Activity activity;
    private EventList modelList;
    private ArrayList<HabitEvent> eventList;


    public EventListAdapter(Activity activity, EventList eventMap) {
        this.activity = activity;
        this.eventList = new ArrayList<>();
        this.modelList = eventMap;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    public void commit(Set<UUID> showTypes, String keyword){
        eventList.clear();
        for(Map.Entry<UUID,HabitEvent> entry : modelList.entrySet()) {
            if (entry.getValue().getComment().contains(keyword)) {
                eventList.add(entry.getValue());
            }
        }
        Collections.sort(eventList,Collections.<HabitEvent>reverseOrder());
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }


        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image_view);
        TextView title = (TextView) view.findViewById(R.id.list_item_title);
        TextView subtitle = (TextView) view.findViewById(R.id.list_item_text_subtitle);
        TextView date = (TextView) view.findViewById(R.id.list_item_date);
        final HabitEvent event = eventList.get(position);
        String encodedImage = event.getEncodedImage();
        if (encodedImage != null) {
            ImageManager imageManager = new ImageManager(event);
            imageManager.setPic(imageView);
        }
        title.setText(event.getName());
        subtitle.setText(event.getComment());
        date.setText(Constant.TIME_FORMAT.format(event.getDate()));
        date.setVisibility(View.VISIBLE);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ActivityModifyEvent.class);
                intent.putExtra(Constant.EXTRA_ID,event.getUid().toString());
                activity.startActivityForResult(intent,Constant.REQUEST_MODIFY_EVENT);
            }
        });

        return view;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }
}