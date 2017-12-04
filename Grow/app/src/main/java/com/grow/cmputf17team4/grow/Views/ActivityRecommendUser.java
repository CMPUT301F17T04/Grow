package com.grow.cmputf17team4.grow.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.grow.cmputf17team4.grow.Controllers.ESManager;
import com.grow.cmputf17team4.grow.R;

import java.util.List;

/**
 * Created by jack27 on 04/12/17.
 */

public class ActivityRecommendUser extends AppCompatActivity {
    private ListView mListView;
    private ProgressBar progressBar;
    private LinearLayout mlinearLayout;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_user);
        mListView = (ListView)findViewById(R.id.recommend_user_list);


        AsyncTask task = new ESManager.SearchFriendsTask(new PreExecuteRunnable(),
                new SuccessPostExecuteRunnable(),
                new FailedPostExecuteRunnable());

    }

    // Runnable when fetching data is successful
    private class SuccessPostExecuteRunnable implements Runnable{
        @Override
        public void run(){

        }
    }

    // Runnable before fetching data
    private class PreExecuteRunnable implements Runnable{
        @Override
        public void run(){

        }
    }

    // Runnable when fetching data is successful
    private class FailedPostExecuteRunnable implements Runnable{
        @Override
        public void run(){

        }
    }



}
