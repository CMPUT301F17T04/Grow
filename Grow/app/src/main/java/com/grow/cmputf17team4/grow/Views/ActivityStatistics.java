package com.grow.cmputf17team4.grow.Views;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;

public class ActivityStatistics extends AppCompatActivity {

    private TextView userName;
    private ArrayList<DataPoint> aMonthlyAchievement;
    private ArrayList<DataPoint> aIntervalFrequency;
    private HabitType habitType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // get HabitType from
        int index = getIntent().getIntExtra(Constant.EXTRA_INDEX,-1);
        habitType = Cache.getInstance().getHabitTypes().get(index);

        // get arrayList of monthlyAchievenment
        getMonthlyAchievement();
        DataPoint[] monthlyAchievement = aMonthlyAchievement.toArray(new DataPoint[aMonthlyAchievement.size()]);

        // draw the monthly achievement
        GraphView graph = (GraphView) findViewById(R.id.graph_monthly_achievement);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(monthlyAchievement);
        series.setDrawValuesOnTop(true);
        graph.addSeries(series);

        // get arrayList of intervalFrequency
        getMonthlyAchievement();
        DataPoint[] intervalFrequency = aIntervalFrequency.toArray(new DataPoint[aIntervalFrequency.size()]);

        // draw the monthly achievement
        GraphView graph1 = (GraphView) findViewById(R.id.graph_interval_frequency);
        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(intervalFrequency);
        series1.setDrawValuesOnTop(true);
        graph1.addSeries(series1);


    }

    public void getMonthlyAchievement(){
        // traverse the habit event history
        HabitEvent habitEvent = habitType.getMostRecentEvent();
        EventList eventList = DataManager.getInstance().getEventList();
        int[] monthFrequency = new int[12];
        while(true){
            Date eventDate = habitEvent.getDate();
            int month = eventDate.getMonth();
            monthFrequency[month] += 1;

            String eventString = habitEvent.getPrevEvent();
            if (eventString == null){
                // if eventString is null, then we reach the end
                break;
            }
            habitEvent = eventList.get(eventString);
        }

        // copy monthFrequency to aMonthlyAchievement
        for(int i = 0; i<12; i++){
            aMonthlyAchievement.add(new DataPoint(i,monthFrequency[i]));
        }
    }

    public void getIntervalFrequency(){
        HabitEvent habitEvent = habitType.getMostRecentEvent();
        EventList eventList = DataManager.getInstance().getEventList();
        int[] hourFreq = new int[24];
        while(true){
            Date eventDate = habitEvent.getDate();
            int hour = eventDate.getHours();
            hourFreq[hour] += 1;

            String eventString = habitEvent.getPrevEvent();
            if (eventString == null){
                // if eventString is null, then we reach the end
                break;
            }
            habitEvent = eventList.get(eventString);
        }

        // copy monthFrequency to aMonthlyAchievement
        for(int i = 0; i < 24; i++){
            aIntervalFrequency.add(new DataPoint(i,hourFreq[i]));
        }


    }


}
