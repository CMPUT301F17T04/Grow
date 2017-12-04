package com.grow.cmputf17team4.grow.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Models.App;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Models.ItemList;
import com.grow.cmputf17team4.grow.Models.User;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;

import java.util.ArrayList;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_MODIFY_EVENT;
import static com.grow.cmputf17team4.grow.Models.Constant.TYPE_FOLLOWINGS;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }


        ImageView imageView =  view.findViewById(R.id.list_item_image_view);
        TextView title =  view.findViewById(R.id.list_item_title);
        TextView subtitle = view.findViewById(R.id.list_item_text_subtitle);
        TextView date = view.findViewById(R.id.list_item_date);
        CardView card = view.findViewById(R.id.list_item_card);
        ImageManager imageManager;
        final ImageButton accept = view.findViewById(R.id.list_item_btn_accept);
        ImageButton reject = view.findViewById(R.id.list_item_btn_reject);

        if (i < requestList.size()){
            final User user = (User) getItem(i);
            card.setCardBackgroundColor(App.getContext().getColor(R.color.colorAccent));
            title.setText(user.getUid());
            subtitle.setText("want to follow you");
            title.setTextColor(Color.WHITE);
            subtitle.setTextColor(Color.WHITE);
            imageManager= new ImageManager(user);
            imageManager.setPic(imageView);
            accept.setVisibility(View.VISIBLE);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new ESManager.AppendTask(TYPE_FOLLOWINGS).execute(user.getUid());
                    new Cache.RemoveTask(activity).execute(user.getUid());
                }
            });
            reject.setVisibility(View.VISIBLE);
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Cache.RemoveTask(activity).execute(user.getUid());
                }
            });
            date.setVisibility(View.GONE);
        } else {
            title.setTextColor(activity.getColor(R.color.colorTextPrimary));
            subtitle.setTextColor(activity.getColor(R.color.colorTextPrimary));
            date.setVisibility(View.VISIBLE);
            HabitType type = (HabitType) getItem(i);
            card.setCardBackgroundColor(Color.WHITE);
            title.setText(type.getName());
            subtitle.setText("@"+type.getUserId());
            HabitEvent event = type.getMostRecentEvent();
            accept.setVisibility(View.GONE);
            reject.setVisibility(View.GONE);
            date.setText("No event completed");
            imageManager = new ImageManager(event);
            imageManager.setPic(imageView);
            if (event != null){
                date.setText("Most recent: " + Constant.TIME_FORMAT.format(event.getDate()));
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
