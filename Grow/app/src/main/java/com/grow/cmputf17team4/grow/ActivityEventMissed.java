package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity shows when the habit event is not finished
 * @author Yizhou Zhao
 */
public class ActivityEventMissed extends AppCompatActivity implements View.OnClickListener{

    private TextView message;
    private Button next;
    private String file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_missed);
    }

    @Override
    public void onClick(View view){

    }

    public void saveInFile(){
        
    }
}
