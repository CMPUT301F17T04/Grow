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
    private int user_id;

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
                //user_id = userList.getCount();
                boolean sign = true;
                if (username.length() == 0){
                    username.setError("User name cannot be empty");
                    sign = false;
                }

                User user = new User(nameString);
                if (existedUser(nameString)) {
                    try{
                        Toast.makeText(getApplicationContext(),"User name is registered", Toast.LENGTH_LONG).show();
                        throw new IllegalArgumentException("User Exists");
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

    /**
     * The method to call elastic search to check if the login user name is registered
     * @param user_name the log in user name
     * @return if the user is registered
     */
    private boolean existedUser (String user_name) {
        ElasticSearchHelper.IsExist isExist = new ElasticSearchHelper.IsExist();
        isExist.execute(user_name);

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
