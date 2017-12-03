package com.grow.cmputf17team4.grow.Models;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by qin7 on 2017/12/2.
 */

public class FakeTestingData {
    @Test
    public void test(){
        assert(DataManager.getInstance().getUser() != null);
        Calendar calendar = Calendar.getInstance();
        int num_days = 100;
        calendar.add(-num_days,Calendar.DATE);
        HabitType type = new HabitType();
        type.setName("Play BasketBall");
        for (int i = 0; i < 7; ++i){
            type.setRepeat(i,true);
        }
        DataManager.getInstance().getHabitList().add(type);
        Random random =  new Random();
        HabitEvent event;
        for (int i = 0; i < num_days; i++){
            if (random.nextFloat()< 0.8){
                event = new HabitEvent(type.getUid());
                event.set
                DataManager.getInstance().getEventList().add(event);
            }
        }



    }
}
