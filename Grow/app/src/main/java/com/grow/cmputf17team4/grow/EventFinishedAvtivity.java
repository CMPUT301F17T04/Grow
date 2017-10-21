package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Activity when user finished his/her habit event
 * @author Yizhou Zhao
 */
public class EventFinishedAvtivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton attachedPhoto;
    private Switch attachLocation;
    private Button finish;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_finished_avtivity);
    }

    @Override
    public void onClick(View view){

    }
}
