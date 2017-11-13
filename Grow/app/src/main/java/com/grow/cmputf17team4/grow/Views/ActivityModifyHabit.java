package com.grow.cmputf17team4.grow.Views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class ActivityModifyHabit extends AppCompatActivity {
    private EditText editName;
    private EditText editDate;
    private EditText editReason;
    private HabitType habit;
    private CheckBox[] checkBoxes;
    private ActivityModifyHabit that = this;
    private Calendar myCalendar;
    private  int requestCode;
    private HabitList habitList;
    private UUID uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_habit);
        Intent intent = getIntent();
        requestCode = intent.getIntExtra("requestCode", Constant.REQUEST_NONE);
        checkBoxes = new CheckBox[7];
        that = this;
        myCalendar = Calendar.getInstance();

        // get views
        editName = (EditText) findViewById(R.id.modify_habit_edit_name);
        editDate = (EditText) findViewById(R.id.modify_habit_edit_date);
        editReason = (EditText) findViewById(R.id.modify_habit_edit_reason);
        checkBoxes[0] = (CheckBox) findViewById(R.id.modify_habit_checkbox_0);
        checkBoxes[1] = (CheckBox) findViewById(R.id.modify_habit_checkbox_1);
        checkBoxes[2] = (CheckBox) findViewById(R.id.modify_habit_checkbox_2);
        checkBoxes[3] = (CheckBox) findViewById(R.id.modify_habit_checkbox_3);
        checkBoxes[4] = (CheckBox) findViewById(R.id.modify_habit_checkbox_4);
        checkBoxes[5] = (CheckBox) findViewById(R.id.modify_habit_checkbox_5);
        checkBoxes[6] = (CheckBox) findViewById(R.id.modify_habit_checkbox_6);

        habitList = (HabitList)DataManager.getInstance().getHabitList();
        if (requestCode == Constant.REQUEST_MODIFY_HABIT){
            uid = UUID.fromString(intent.getStringExtra(Constant.EXTRA_ID));
            habit = habitList.get(uid);
        } else if (requestCode == Constant.REQUEST_CREATE_HABIT){
            habit  = new HabitType();
            findViewById(R.id.modify_habit_btn_delete).setVisibility(View.GONE);
            findViewById(R.id.modify_habit_btn_statstic).setVisibility(View.GONE);
        } else {
            throw new Error("Unknown Request");
        }

        // code from https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editDate.setText(Constant.TIME_FORMAT.format(myCalendar.getTime()));
            }

        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(that, dateListener,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        editName.setText(habit.getName());
        editReason.setText(habit.getReason());
        for (int i = 0; i < 7; ++i){
            checkBoxes[i].setChecked(habit.getRepeat(i));
        }
        editDate.setText(Constant.TIME_FORMAT.format(habit.getStartDate()));
        myCalendar.setTime(habit.getStartDate());
    }

    public void onCreateHabitConfirm(View v){
        if (editName.getText().toString().replace(" ","").isEmpty()){
            Toast.makeText(that,"Habit Name cannot be empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean any_checked = false;
        for (int i = 0; i < 7; ++i){
            any_checked = any_checked || checkBoxes[i].isChecked();
        }
        if (! any_checked){
            Toast.makeText(that,"Choose At Least One Repeat!",Toast.LENGTH_SHORT).show();
            return;
        }

        habit.setName(editName.getText().toString());
        habit.setReason(editReason.getText().toString());
        for (int i = 0; i < 7; ++i){
            habit.setRepeat(i,checkBoxes[i].isChecked());
        }
        habit.setStartDate(myCalendar.getTime());

        if (requestCode == Constant.REQUEST_CREATE_HABIT){
            habitList.add(habit);
        }
        that.setResult(RESULT_OK);
        that.finish();
    }

    public void onModifyHabitDelete(View v){
        habitList.remove(uid);
        this.setResult(RESULT_OK);
        this.finish();
    }

}
