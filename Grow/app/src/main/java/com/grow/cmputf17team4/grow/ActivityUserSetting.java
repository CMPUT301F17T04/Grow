package com.grow.cmputf17team4.grow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityUserSetting extends AppCompatActivity {

    private EditText userName;
    private ImageButton picture;
    private TextView completed;
    private TextView finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
    }
}
