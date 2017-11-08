package com.grow.cmputf17team4.grow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private EditText Username;
    private Button Login;
    private Button Register;
    private String NameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = (EditText) findViewById(R.id.user_log);

        Login = (Button) findViewById(R.id.log_in);
        Register = (Button) findViewById(R.id.register);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameString = Username.getText().toString();
                //Log.d("username", userName);
                if (existedUser(NameString)) {
                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    Log.d("username", NameString);
                    storePreference(NameString);
                    startActivity(intent);
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
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
                Toast.makeText(getApplicationContext(), user_name + " User does not exist.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * The method to store the user login name in a local file
     * @param name String of user name to be stored
     */
    private void storePreference(String name){
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("currentUser",name);
        editor.apply();
    }
}
