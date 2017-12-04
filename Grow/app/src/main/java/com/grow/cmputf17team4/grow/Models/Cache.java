package com.grow.cmputf17team4.grow.Models;

import android.app.Activity;
import android.os.AsyncTask;

import com.grow.cmputf17team4.grow.Controllers.CommunityAdapter;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ESManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by qin7 on 2017/12/3.
 */

public class Cache {
    private static final Cache ourInstance = new Cache();
    private ArrayList<User> requesters;
    private ArrayList<HabitType> habitTypes;
    private ArrayList<String> followings;
    private CommunityAdapter adapter;
    private boolean init;

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
            updated = true;
            requsterIds.setChanged(false);
            DataManager.getInstance().getBuffer().update(Constant.QUERY_UPDATE,requsterIds);
        }

        IDList followingIds=(IDList) ESManager.get(DataManager.getInstance().getUser().getUid(),Constant.TYPE_FOLLOWINGS,IDList.class);
        if (followingIds == null){
            return updated;
        }
        if (followingIds.isChanged()||ourInstance.init){
            ourInstance.followings.clear();
            ourInstance.followings.addAll(followingIds.getPayload());
            updated = true;
            requsterIds.setChanged(false);
            DataManager.getInstance().getBuffer().update(Constant.QUERY_UPDATE,requsterIds);
        }
        if (updated){
            DataManager.save();
            ourInstance.adapter.commit();
        }

        if (ourInstance.init){
            ourInstance.init = false;
            new FetchTask().execute();
        }

        return updated;
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

}
