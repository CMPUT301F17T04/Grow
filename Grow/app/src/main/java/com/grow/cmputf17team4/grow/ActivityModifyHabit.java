package com.grow.cmputf17team4.grow;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ActivityModifyHabit extends AppCompatActivity {
    private EditText editName;
    private EditText editDate;
    private EditText editReason;
    private HabitType habit;
    private Gson gson;
    private CheckBox[] checkBoxes;
    private ActivityModifyHabit that = this;
    private Calendar myCalendar;
    private SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_habit);
        Intent intent = getIntent();
        int requestCode = intent.getIntExtra("requestCode",Constant.REQUEST_NONE);
        gson = new Gson();
        checkBoxes = new CheckBox[7];
        that = this;
        myCalendar = Calendar.getInstance();
        format = new SimpleDateFormat("yyyy-MM-dd");

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


        if (requestCode == Constant.REQUEST_MODIFY_HABIT){
            habit = gson.fromJson(intent.getStringExtra(Constant.EXTRA_HABIT_TYPE),HabitType.class);
            myCalendar.setTime(habit.getStartDate());
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
                editDate.setText(format.format(myCalendar.getTime()));
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
        editDate.setText(format.format(myCalendar.getTime()));
    }

    public void onCreateHabitConfirm(View v){
        if (editName.getText().toString().replace(" ","").isEmpty()){
            Toast.makeText(that,"Habit Name cannot be empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        habit.setName(editName.getText().toString());
        habit.setReason(editReason.getText().toString());
        for (int i = 0; i < 7; ++i){
            habit.setRepeat(i,checkBoxes[i].isChecked());
        }
        habit.setStartDate(myCalendar.getTime());

        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_HABIT_TYPE,gson.toJson(habit));
        that.setResult(RESULT_OK,intent);
        that.finish();
    }

}
