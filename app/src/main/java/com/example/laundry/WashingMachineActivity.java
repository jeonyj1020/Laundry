package com.example.laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class WashingMachineActivity extends AppCompatActivity {



    private final String packageName = "com.sec.android.app.clockpackage";
    Button to_alarm;
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rBtnCalendar, rBtnTime;
    CalendarView calView;
    TimePicker tPicker;
    int selectYear, selectMonth, selectDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_machine);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);

        chrono = (Chronometer) findViewById(R.id.chrono);

        rBtnCalendar = (RadioButton)findViewById(R.id.rBtnCalendar);
        rBtnTime =(RadioButton)findViewById(R.id.rBtnTime);

        tPicker = (TimePicker)findViewById(R.id.timePicker);
        calView = (CalendarView)findViewById(R.id.calendarView);

        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);

        rBtnCalendar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
            }
        });

        rBtnTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                Toast.makeText(getApplicationContext(),
                        Integer.toString(selectYear)+"년 " +
                                Integer.toString(selectMonth)+"월 " +
                                Integer.toString(selectDay)+"일 " +
                                Integer.toString(tPicker.getCurrentHour())+"시 " +
                                Integer.toString(tPicker.getCurrentMinute())+"분 저장됨"

                        , Toast.LENGTH_LONG).show();


            }



        });

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear =year;
                selectMonth =month + 1;
                selectDay = dayOfMonth;
            }
        });

        to_alarm = (Button)findViewById(R.id.to_alarm);
        Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
        to_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WashingMachineActivity.this.startActivity(intent);
            }
        });
    }


}