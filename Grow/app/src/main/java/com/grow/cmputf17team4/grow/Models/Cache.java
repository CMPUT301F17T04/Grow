package com.grow.cmputf17team4.grow.Models;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.grow.cmputf17team4.grow.Controllers.CommunityAdapter;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ESManager;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Views.ActivityMain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Represent...
 */
public class Cache {
    private static final Cache ourInstance = new Cache();
    private ArrayList<User> requesters;
    private ArrayList<HabitType> habitTypes;
    private ArrayList<String> followings;
    private CommunityAdapter adapter;
    private boolean init;
    private static int nid = 0;

    public static Cache getInstance() {
        return ourInstance;
    }

    private Cache() {
        requesters = new ArrayList<>();
        habitTypes = new ArrayList<>();
        followings = new ArrayList<>();
        init = true;
    }


    public static boolean checkUpdates(){
        boolean updated = false;
        IDList requsterIds=(IDList) ESManager.get(DataManager.getInstance().getUser().getUid(),Constant.TYPE_REQUESTS,IDList.class);
        if (requsterIds == null){
            return false;
        }
        ArrayList<User> newRequesters = new ArrayList<>();
        if (requsterIds.isChanged() || ourInstance.init){
            User user;
            for (String id: requsterIds.getPayload()){
                user = (User)ESManager.get(id,Constant.TYPE_USER,User.class);
                if (user == null){
                    return false;
                }
                newRequesters.add(user);
            }
            ourInstance.requesters.clear();
            ourInstance.requesters.addAll(newRequesters);
            requsterIds.setChanged(false);
            if (!ESManager.update(requsterIds)){return false;}
            updated = true;
        }

        IDList followingIds=(IDList) ESManager.get(DataManager.getInstance().getUser().getUid(),Constant.TYPE_FOLLOWINGS,IDList.class);
        if (followingIds == null){
            return updated;
        }
        if (followingIds.isChanged()||ourInstance.init){
            ourInstance.followings.clear();
            ourInstance.followings.addAll(followingIds.getPayload());
            updated = true;
            followingIds.setChanged(false);
            if (! ESManager.update(followingIds)){return updated;}
        }
        if (updated){
            ourInstance.adapter.commit();
        }

        if (ourInstance.init){
            ourInstance.init = false;
            new FetchTask().execute();
        }

        return updated;
    }



    public static class RemoveTask extends AsyncTask<String,Void,Integer>{
        private Activity activity;
        public RemoveTask(Activity activity){
            this.activity = activity;
        }
        @Override
        protected Integer doInBackground(String... strings) {
            IDList requesterIds = (IDList) ESManager.get(DataManager.getInstance().getUser()
                    .getUid(),Constant.TYPE_REQUESTS,IDList.class);
            if (requesterIds == null){
                return Constant.TASK_EXCEPTION;
            }

            requesterIds.setChanged(false);
            requesterIds.getPayload().remove(strings[0]);

            User user;
            ArrayList<User> newRequesters = new ArrayList<>();
            for (String id: requesterIds.getPayload()){
                user = (User)ESManager.get(id,Constant.TYPE_USER,User.class);
                if (user == null){
                    return Constant.TASK_EXCEPTION;
                }
                newRequesters.add(user);
            }
            if (!ESManager.update(requesterIds)){
                return Constant.TASK_EXCEPTION;
            }
            ourInstance.requesters.clear();
            ourInstance.requesters.addAll(newRequesters);
            return Constant.TASK_SUCCESS;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer==Constant.TASK_EXCEPTION){
                Toast.makeText(activity, R.string.internet_error,Toast.LENGTH_SHORT).show();
            } else {
                ourInstance.adapter.commit();
            }
        }
    }

    public static class FetchTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            ArrayList<String> habitTypeIds = new ArrayList<>();
            User user;
            for (String id : ourInstance.followings){
                user = (User) ESManager.get(id,Constant.TYPE_USER,User.class);
                if (user == null){
                    return false;
                }
                habitTypeIds.addAll(user.getHabitList());
            }

            ArrayList<HabitType> result = new ArrayList<>();
            HabitType habitType;
            for (String id: habitTypeIds){
                habitType = (HabitType) ESManager.get(id,Constant.TYPE_HABIT_TYPE,HabitType.class);
                if (habitType == null){
                    return false;
                }
                result.add(habitType);
            }
            ourInstance.habitTypes.clear();
            ourInstance.habitTypes.addAll(result);
            Collections.sort(ourInstance.habitTypes, new Comparator<HabitType>() {
                @Override
                public int compare(HabitType habitType, HabitType t1) {
                    int result = habitType.getUserId().compareTo(t1.getUserId());
                    if (result == 0) {
                        return habitType.getName().compareTo(t1.getName());
                    }
                    return result;
                }
            });
            ourInstance.adapter.commit();
            return true;
        }
    }

    public static CommunityAdapter getAdapter(Activity activity){
        ourInstance.adapter =  new CommunityAdapter(activity,ourInstance.requesters,ourInstance.habitTypes);
        return ourInstance.adapter;
    }

    public ArrayList<HabitType> getHabitTypes() {
        return habitTypes;
    }
}
