package com.grow.cmputf17team4.grow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity to create Habit type
 * @author Yizhou Zhao
 */
public class ActivityCreateHabit extends AppCompatActivity implements View.OnClickListener{

    private EditText habitName;
    private EditText habitReason;
    private EditText duration;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);
    }

    @Override
    public void onClick(View view){

    }

}
