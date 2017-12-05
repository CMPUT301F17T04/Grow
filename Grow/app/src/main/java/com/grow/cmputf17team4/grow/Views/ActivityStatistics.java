package com.grow.cmputf17team4.grow.Views;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ActivityStatistics extends AppCompatActivity {

    private TextView numComplete;
    private List<Integer> aMonthlyAchievement = new ArrayList<Integer>();
    private List<Integer> aIntervalFrequency = new ArrayList<Integer>();
    private HabitType habitType;
    private XYPlot graph_month_achievement;
    private XYPlot graph_interval_frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // get HabitType from
        String uid = getIntent().getStringExtra(Constant.EXTRA_ID);
        habitType = DataManager.getInstance().getHabitList().get(uid);
        numComplete = (TextView) findViewById(R.id.stat_complete);
        int num = habitType.getNumCompleted();
        numComplete.setText(Integer.toString(num));
        ((TextView)findViewById(R.id.statistics_habit_name)).setText(habitType.getName());


        // initialize our XYPlot reference:
        graph_month_achievement = (XYPlot) findViewById(R.id.graph_monthly_achievement);
        graph_interval_frequency= (XYPlot)findViewById(R.id.graph_interval_frequency);
        getMonthlyAchievement();
        getIntervalFrequency();

        graph_month_achievement.setDomainStepValue(1.0);
        graph_month_achievement.setRangeStepValue(1.0);
        XYSeries series1 = new SimpleXYSeries(
                aMonthlyAchievement, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "# completed");

        BarFormatter bf = new BarFormatter(Color.WHITE, Color.WHITE);
        graph_month_achievement.addSeries(series1, bf);


        graph_interval_frequency.setDomainStepValue(1.0);
        graph_interval_frequency.setRangeStepValue(1.0);
        XYSeries series2 = new SimpleXYSeries(
                aIntervalFrequency, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "# completed");

        graph_interval_frequency.addSeries(series2, bf);

    }

    public void getMonthlyAchievement(){
        // traverse the habit event history
        HabitEvent habitEvent = habitType.getMostRecentEvent();
        EventList eventList = DataManager.getInstance().getEventList();
        int[] monthFrequency = new int[12];
        while(true){
            Date eventDate = habitEvent.getDate();
            int month = eventDate.getMonth();

            Log.d("statistics",Integer.toString(month));
            monthFrequency[month] += 1;

            String eventString = habitEvent.getPrevEvent();
            if (eventString == null){
                // if eventString is null, then we reach the end
                break;
            }
            habitEvent = eventList.get(eventString);
        }

        // copy monthFrequency to aMonthlyAchievement
        aMonthlyAchievement.add(0);
        for(int i = 0; i<12; i++){
            aMonthlyAchievement.add(monthFrequency[i]);
        }
        aMonthlyAchievement.add(0);
    }

    public void getIntervalFrequency(){
        HabitEvent habitEvent = habitType.getMostRecentEvent();
        EventList eventList = DataManager.getInstance().getEventList();
        int[] hourFreq = new int[24];
        while(true){
            Date eventDate = habitEvent.getDate();

            int hour = eventDate.getHours();
            Log.d("statistics",Integer.toString(hour));
            hourFreq[hour] += 1;
            String eventString = habitEvent.getPrevEvent();
            if (eventString == null){
                // if eventString is null, then we reach the end
                break;
            }
            habitEvent = eventList.get(eventString);
        }

        // copy monthFrequency to aMonthlyAchievement
        aIntervalFrequency.add(0);
        for(int i = 0; i < 24; i++){
            //Log.d("statistics","------");
            //Log.d("statistics", Integer.toString(i));
            //Log.d("statistics",Integer.toString(hourFreq[i]));
            aIntervalFrequency.add(hourFreq[i]);
        }
        aIntervalFrequency.add(0);
    }


}
