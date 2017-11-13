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

public class ActivityModifyEvent extends AppCompatActivity {

    private ImageManager imageManager;
    private ImageView imageView;
    private int requestCode;
    private EditText comment;
    private Switch attachedLocation;
    private HabitEvent event;
    private HabitType habit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);
        Intent intent = getIntent();
        requestCode = intent.getIntExtra("requestCode", Constant.REQUEST_NONE);

        imageView = (ImageView)findViewById(R.id.modify_event_image_view);
        comment = (EditText) findViewById(R.id.modify_event_edit_comment);
        attachedLocation = (Switch) findViewById(R.id.modify_event_attached_location);

        if (requestCode == Constant.REQUEST_COMPLETE_EVENT){
            String id = intent.getStringExtra(Constant.EXTRA_ID);
            if (id==null){throw new Error("No ID in intent");}
            habit = DataManager.getInstance().getHabitList().get(UUID.fromString(id));
            findViewById(R.id.modify_event_btn_delete).setVisibility(View.GONE);
            event = habit.buildEvent();
        } else if (requestCode == Constant.REQUEST_MODIFY_EVENT){
            event = DataManager.getInstance().getEventList().get(UUID.fromString(intent.getStringExtra("id")));
            findViewById(R.id.modify_event_text).setVisibility(View.GONE);
        } else {
            throw new Error("Unknown Request");
        }
        imageManager = new ImageManager(event);
        comment.setText(event.getComment());
        attachedLocation.setChecked(event.isAttachedLocation());
        imageManager.setPic(imageView);
    }

    public void onModifyEventDelete(View v){
        DataManager.getInstance().getEventList().remove(event.getUid());
        setResult(RESULT_OK);
        finish();
    }

    public void onModifyEventConfirm(View v){
        event.setComment(comment.getText().toString());
        event.setAttachedLocation(attachedLocation.isChecked());
        if (requestCode == Constant.REQUEST_COMPLETE_EVENT){
            DataManager.getInstance().getEventList().add(event);
            habit.getRecord().add(event.getDate());
        }
        setResult(RESULT_OK);
        finish();
    }

    public void onEventTakePhoto(View v){
        imageManager.dispatchTakePictureIntent(this);
    }

    public void onEventPickImage(View v){
        imageManager.getPictureFromGalleryIntent(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == Constant.REQUEST_TAKE_PHOTO ){
            imageManager.encode(imageView);
        } else if (requestCode == Constant.REQUEST_PICK_IMAGE){
            imageManager.encode(imageView,data.getData());
        }
    }

}
