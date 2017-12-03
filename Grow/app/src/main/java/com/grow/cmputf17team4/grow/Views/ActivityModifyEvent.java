package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ImageManager;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_PICK_IMAGE;
import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_TAKE_PHOTO;

/**
 * Activity for modifying habit event
 * @author Qin Zhang
 * @since 1.0
 */
public class ActivityModifyEvent extends AppCompatActivity {

    private ImageManager imageManager;
    private ImageView imageView;
    private int requestCode;
    private EditText comment;
    private Switch attachedLocation;
    private HabitEvent event;
    private HabitType habit;
    /**
     * OnCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);
        Intent intent = getIntent();
        requestCode = intent.getIntExtra("requestCode", Constant.REQUEST_NONE);

        imageView = findViewById(R.id.modify_event_image_view);
        comment =  findViewById(R.id.modify_event_edit_comment);
        attachedLocation =  findViewById(R.id.modify_event_attached_location);

        if (requestCode == Constant.REQUEST_COMPLETE_EVENT){
            String id = intent.getStringExtra(Constant.EXTRA_ID);
            if (id==null){throw new Error("No ID in intent");}
            habit = DataManager.getInstance().getHabitList().get(id);
            findViewById(R.id.modify_event_btn_delete).setVisibility(View.GONE);
            event = new HabitEvent(habit.getUid());
        } else if (requestCode == Constant.REQUEST_MODIFY_EVENT){
            event = DataManager.getInstance().getEventList().get(intent.getStringExtra(Constant.EXTRA_ID));
            TextView textView =  findViewById(R.id.modify_event_text);
            textView.setText(event.getName());
        } else {
            throw new Error("Unknown Request");
        }
        imageManager = new ImageManager(event);
        comment.setText(event.getComment());
        attachedLocation.setChecked(event.isAttachedLocation());
        imageManager.setPic(imageView);
    }
    /**
     * Delete the habit event
     * @param v
     */
    public void onModifyEventDelete(View v){
        DataManager.getInstance().getEventList().remove(event.getUid());
        setResult(RESULT_OK);
        finish();
    }
    /**
     * Confirm the change
     * @param v
     */
    public void onModifyEventConfirm(View v){
        event.setComment(comment.getText().toString());
        event.setAttachedLocation(attachedLocation.isChecked(), this);
        if (requestCode == Constant.REQUEST_COMPLETE_EVENT){
            DataManager.getInstance().getEventList().add(event);
        } else {
            DataManager.getInstance().getEventList().commit(event.getUid());
        }
        setResult(RESULT_OK);
        finish();
    }
    /**
     * Called if the user to take photo in order to attach photo
     * @param v
     */
    public void onEventTakePhoto(View v){
        imageManager.dispatchTakePictureIntent(this);
    }
    /**
     * Called if the user decide to choose photo in gallery in order to attach photo
     * @param v
     */
    public void onEventPickImage(View v){
        imageManager.getPictureFromGalleryIntent(this);
    }
    /**
     * Override the onActivityResult method
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_TAKE_PHOTO ){
            imageManager.encode(imageView);
        } else if (requestCode == REQUEST_PICK_IMAGE){
            imageManager.encode(imageView,data.getData());
        }
    }

}
