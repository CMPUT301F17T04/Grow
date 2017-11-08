package com.grow.cmputf17team4.grow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends AppCompatActivity {

    private EditText username;
    private Button register;
    private String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.user_log);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = username.getText().toString();
                //uid = userList.getCount();
                boolean sign = true;
                if (username.length() == 0){
                    username.setError("User name cannot be empty");
                    sign = false;
                }

                User user = new User(nameString);
                if (existedUser(nameString)) {
                    try{
                        Toast.makeText(getApplicationContext(),"Duplicate user name!", Toast.LENGTH_LONG).show();
                        throw new IllegalArgumentException("Duplicate user");
                    }catch(IllegalArgumentException e){

                    }
                }

                else {
                    //userList.addUser(user);
                    ElasticSearchHelper.AddUserTask addUserTask
                            = new ElasticSearchHelper.AddUserTask();
                    addUserTask.execute(user);
                    if (sign) {
                        Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean existedUser (String name) {
        ElasticSearchHelper.IsExist isExist = new ElasticSearchHelper.IsExist();
        isExist.execute(name);

        try {
            if (isExist.get()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
