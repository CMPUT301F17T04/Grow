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
import android.view.View;
import android.widget.ImageView;

import com.grow.cmputf17team4.grow.Controllers.ImageManager;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityModifyEvent extends AppCompatActivity {

    ImageManager imageManager;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);
        imageManager = ImageManager.getInstance();
        imageView = (ImageView)findViewById(R.id.modify_event_image_view);
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
            imageManager.setPic(imageView);
        } else if (requestCode == Constant.REQUEST_PICK_IMAGE){
            imageManager.setPic(this,imageView,data.getData());
        }
    }
}
