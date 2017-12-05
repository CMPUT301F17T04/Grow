package com.grow.cmputf17team4.grow.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Controllers.ESManager;
import com.grow.cmputf17team4.grow.R;

import java.util.List;

/**
 * Activity of Wow factor, recommend other users based on current user's habit
 * @author Yizhou Zhao
 * @author Qin Zhang
 * @since 1.0
 */

public class ActivityRecommendUser extends AppCompatActivity {
    /**
     * ListView object in the activity
     */
    private ListView mListView;

    /**
     * Progress bar shows before the data is fetching
     */
    private ProgressBar progressBar;

    /**
     * TextView object shows when an exception is thrown
     */
    private TextView textView;

    /**
     * SwipeRefreshLayout so that user can update the data
     */
    private SwipeRefreshLayout swipeRefreshLayout;


    /**
     * Override super.onCreate(). Connect UI component and layout file, loading data.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_user);

        mListView = findViewById(R.id.recommend_user_list);
        textView = findViewById(R.id.recommend_user_text_error);
        progressBar = findViewById(R.id.recommend_user_progress_bar);

        swipeRefreshLayout =  findViewById(R.id.recommend_user_swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });

        loading();
    }

    /**
     * Loding data from elasterSearch server
     */
    private void loading(){
        mListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        new ESManager.SearchFriendsTask(
                new PreExecuteRunnable(),
                new SuccessPostExecuteRunnable(),
                new FailedPostExecuteRunnable()
        ).execute();

    }


    /**
     * Runnable object, will passed to ESManager.
     * Called after the data is successfully fetched
     * @see ESManager
     */
    private class SuccessPostExecuteRunnable implements Runnable{
        @Override
        public void run(){
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);

        }
    }

    /**
     * Runnable object, will passed to ESManager.
     * Called before the data is getting fetched
     * @see ESManager
     */
    private class PreExecuteRunnable implements Runnable{
        @Override
        public void run(){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Runnable object, will passed to ESManager
     * Called after exceptions are thrown when fetching data.
     * @see ESManager
     */
    private class FailedPostExecuteRunnable implements Runnable{
        @Override
        public void run(){
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }

}
