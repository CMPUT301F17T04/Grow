package com.grow.cmputf17team4.grow.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.grow.cmputf17team4.grow.Controllers.CommunityAdapter;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Controllers.ESManager;
import com.grow.cmputf17team4.grow.Controllers.MapManager;
import com.grow.cmputf17team4.grow.Models.Cache;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;
import com.grow.cmputf17team4.grow.Models.User;

import java.util.ArrayList;

import static com.grow.cmputf17team4.grow.Models.Constant.REQUEST_CREATE_HABIT;

/**
 * Fragment shows the 3rd tab in the main activity
 * @author Yizhou Zhao
 */
public class FragmentCommunity extends Fragment {

    private ListView list;
    private EditText searchBox;
    private ArrayList<User> userToDisplay;

    public static FragmentCommunity newInstance(){
        Bundle args = new Bundle();
        FragmentCommunity fragment = new FragmentCommunity();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, null);


        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.community_swipe_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    boolean result;
                    do {
                        result = new Cache.FetchTask().execute().get();
                    } while (!result);
                } catch (Exception e){
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ListView listView = view.findViewById(R.id.community_list_view);
        listView.setAdapter(Cache.getAdapter(getActivity()));



        getActivity().findViewById(R.id.toolbar_btn_friend_events_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HabitEvent> events = new ArrayList<>();
                HabitEvent event;
                for (HabitType type: Cache.getInstance().getHabitTypes()){
                    event = type.getMostRecentEvent();
                    if (event != null){
                        event.setName(type.getName());
                        events.add(event);
                    }
                }
                MapManager.getInstance().setHabitEventList(events);
                Intent intent = new Intent(getActivity(),ActivityHabitMap.class);
                intent.putExtra("intentFrom",false);
                startActivity(intent);
            }
        });

        // When following button is clicked

        getActivity().findViewById(R.id.toolbar_btn_follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.toolbar_btn_follow));
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.community_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.community_menu_recommond){
                            // intent to the recommend activity
                        }else if(id == R.id.community_menu_search){
                            // show the search dialog
                            showDialog();
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
        return view;
    }

    public void showDialog(){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog, null);
        final EditText editText = view.findViewById(R.id.dialog_edit_id);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = editText.getText().toString().trim();
                        if (uid.equals(DataManager.getInstance().getUser().getUid())){
                            Toast.makeText(getContext(),"Cannot follow yourself",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            int result = new ESManager.CheckExistTask().execute(uid).get();
                            switch (result){
                                case Constant.TASK_EXCEPTION:
                                    Toast.makeText(getContext(),R.string.internet_error,Toast.LENGTH_SHORT).show();
                                    break;
                                case Constant.TASK_FAIL:
                                    Toast.makeText(getContext(),getString(R.string.user_not_found,uid),Toast.LENGTH_SHORT).show();
                                    break;
                                case Constant.TASK_SUCCESS:
                                    int result2 = new ESManager.AppendTask(Constant.TYPE_REQUESTS).execute(uid).get();
                                    switch (result2){
                                        case Constant.TASK_EXCEPTION:
                                            Toast.makeText(getContext(),R.string.internet_error,Toast.LENGTH_SHORT).show();
                                            break;
                                        case Constant.TASK_FAIL:
                                            Toast.makeText(getContext(),"Fail to send request",Toast.LENGTH_SHORT).show();
                                            break;
                                        case Constant.TASK_SUCCESS:
                                            Toast.makeText(getContext(),getString(R.string.request_success,uid),Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            throw new Error("Unhandled Task Result: " + result2);
                                    }
                                    break;
                                default:
                                    throw new Error("Unhandled Task Result: " + result);
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .setView(view)
                .setTitle("Send request for following")
                .create();
        dialog.show();

    }


}
