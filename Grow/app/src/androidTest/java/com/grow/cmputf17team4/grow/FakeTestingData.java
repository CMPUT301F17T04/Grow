package com.grow.cmputf17team4.grow;

import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by qin7 on 2017/12/2.
 */
@RunWith(AndroidJUnit4.class)
public class FakeTestingData {
    @Test
    public void run(){
        DataManager.loadFromFile(InstrumentationRegistry.getTargetContext());
        assert (DataManager.getInstance().getUser() != null);
        fakeEvents("Play BasketBall",100,0.8);
        DataManager.save();
        DataManager.waitAllTaskDone();
    }

    private void fakeEvents(String habitName,int num_days,double complete_rate){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-num_days);
        HabitType type = new HabitType();
        type.setName(habitName);
        for (int i = 0; i < 7; ++i){
            type.setRepeat(i,true);
        }
        DataManager.getInstance().getHabitList().add(type);
        Random random =  new Random();
        HabitEvent event;
        for (int i = 0; i < num_days; i++){
            if (random.nextFloat()< complete_rate){
                event = new HabitEvent(type.getUid());
                event.setDateForTestingOnly(calendar.getTime());
                DataManager.getInstance().getEventList().add(event);
            }
            calendar.add(Calendar.DATE,1);
        }
    }
}
