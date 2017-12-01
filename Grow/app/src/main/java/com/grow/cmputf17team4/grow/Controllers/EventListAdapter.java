package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.ItemList;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_MODIFY_EVENT;

/**
 * Adapter of habit event list
 * @since 1.0
 * @author Qin Zhang
 */
public class EventListAdapter extends BaseAdapter implements ListAdapter {
    private Activity activity;
    private ItemList<HabitEvent> modelList;
    private ArrayList<HabitEvent> eventList;
    /**
     * Construcor of the EventListAdapter
     * @param activity the activity that this adapter will be used in
     * @param eventMap the event list that this adapter will be used for
     */

    public EventListAdapter(Activity activity, ItemList<HabitEvent> eventMap) {
        this.activity = activity;
        this.eventList = new ArrayList<>();
        this.modelList = eventMap;
    }
    /**
     * The size of the list of habit event
     * @return int- the size of the list
     */
    @Override
    public int getCount() {
        return eventList.size();
    }
    /**
     * Called when a the list of habit event is changed
     * @param showTypes
     * @param keyword
     */
    public void commit(HashMap<String,Boolean> showTypes, String keyword){
        eventList.clear();
        for(Map.Entry<String,HabitEvent> entry : modelList.entrySet()) {
            if (entry.getValue().getComment().contains(keyword)
                    && showTypes.get(entry.getValue().getName())) {
                eventList.add(entry.getValue());
            }
        }
        Collections.sort(eventList,Collections.<HabitEvent>reverseOrder());
        notifyDataSetChanged();
    }

    /**
     * Override the getView function so that the adapter can adapt the content of habit events
     * @param position the position of habitEvent object in the eventList
     * @param view the view that the adapter will work with
     * @param parent the parent view.
     * @return the view that will shows the eventList
     */
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
        ImageManager imageManager = new ImageManager(event);
        imageManager.setPic(imageView);
        title.setText(event.getName());
        subtitle.setText(event.getComment());
        date.setText(Constant.TIME_FORMAT.format(event.getDate()));
        date.setVisibility(View.VISIBLE);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ActivityModifyEvent.class);
                intent.putExtra(Constant.EXTRA_ID,event.getUid().toString());
                int requestCode = REQUEST_MODIFY_EVENT;
                intent.putExtra("requestCode", requestCode);
                activity.startActivityForResult(intent, requestCode);
            }
        });

        return view;
    }
    /**
     * Override the getItemId function. will return 0 all the time
     * @param position the position of the habitEvent object in eventList
     * @return the id of that habitEvent object
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }
    /**
     * Override the getItem function. will return the habitEvent object at corresponding position
     * @param position the position of the habitEvent object in eventList
     * @return the habitEvent object at corresponding position.
     */

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }
}
