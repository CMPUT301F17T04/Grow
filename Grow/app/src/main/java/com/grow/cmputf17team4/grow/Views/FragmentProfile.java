package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ImageManager;
import com.grow.cmputf17team4.grow.R;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_PICK_IMAGE;
import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_TAKE_PHOTO;

/**
 * Fragment shows the second tab in the main activity.
 * Shows the map.
 * @author Yizhou Zhao
 */
public class FragmentProfile extends Fragment {

    private ImageManager imageManager;

    public static FragmentProfile newInstance() {
        Bundle args = new Bundle();
        FragmentProfile fragment = new FragmentProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);

        view.findViewById(R.id.profile_btn_pick_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageManager.getPictureFromGalleryIntent(getActivity());
            }
        });

        view.findViewById(R.id.profile_btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageManager.dispatchTakePictureIntent(getActivity());
            }
        });
        return view;
    }



    public void refresh(){
        View view = getView();
        String events = Integer.toString(DataManager.getInstance().getEventList().size());
        String habits = Integer.toString(DataManager.getInstance().getHabitList().size());
        ((TextView)view.findViewById(R.id.profile_text_events)).setText(events);
        ((TextView)view.findViewById(R.id.profile_text_habits)).setText(habits);
        ((TextView)view.findViewById(R.id.profile_text_name)).setText(DataManager.getInstance().getUser().getUid());
        ((TextView)view.findViewById(R.id.profile_text_start)).setText(DataManager.getInstance().getUser().getDate());
        ImageView imageView = view.findViewById(R.id.profile_imageview);
        imageManager = new ImageManager(DataManager.getInstance().getUser());
        imageManager.setPic(imageView);
    }


    public void result(int requestCode, Intent data){
        ImageView imageView = getView().findViewById(R.id.profile_imageview);
        if (requestCode == REQUEST_TAKE_PHOTO ){
            imageManager.encode(imageView);
        } else if (requestCode == REQUEST_PICK_IMAGE){
            imageManager.encode(imageView,data.getData());
        }
    }
}
