package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.App;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.ItemList;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;

import java.util.ArrayList;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_MODIFY_EVENT;

/**
 * Created by qin7 on 2017/12/3.
 */

public class CommunityAdapter  extends BaseAdapter implements ListAdapter {
    private ArrayList<User> requestList;
    ArrayList<HabitType> habitTypes;
    Activity activity;

    public CommunityAdapter(Activity activity,ArrayList<User> requestList, ArrayList<HabitType> habitTypes) {
        this.activity = activity;
        this.requestList = requestList;
        this.habitTypes = habitTypes;
    }

    @Override
    public int getCount() {
        return requestList.size()+habitTypes.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < requestList.size()){
            return requestList.get(i);
        } else {
            return habitTypes.get(i-requestList.size());
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }


        ImageView imageView =  view.findViewById(R.id.list_item_image_view);
        TextView title =  view.findViewById(R.id.list_item_title);
        TextView subtitle = view.findViewById(R.id.list_item_text_subtitle);
        TextView date = view.findViewById(R.id.list_item_date);
        CardView card = view.findViewById(R.id.list_item_card);
        ImageManager imageManager;

        date.setVisibility(View.GONE);
        if (i < requestList.size()){
            User user = (User) getItem(i);
            card.setCardBackgroundColor(App.getContext().getColor(R.color.colorAccent));
            title.setText(user.getUid());
            subtitle.setText("want to follow you");
             imageManager= new ImageManager(user);
            imageManager.setPic(imageView);
        } else {
            HabitType type = (HabitType) getItem(i);
            card.setCardBackgroundColor(App.getContext().getColor(R.color.cardview_light_background));
            title.setText(type.getName());
            subtitle.setText("@"+type.getUserId());
            HabitEvent event = type.getMostRecentEvent();
            if (event != null){
                date.setVisibility(View.VISIBLE);
                date.setText("Most recent: " + Constant.TIME_FORMAT.format(event.getDate()));
                imageManager = new ImageManager(event);
                imageManager.setPic(imageView);
            }
        }


        return view;
    }

    public void commit() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
}
