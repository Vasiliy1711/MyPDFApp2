package com.example.mypdfapp2.ui.act_base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mypdfapp2.data.DaysDatabase;
import com.example.mypdfapp2.data.TimetableDatabase;

public class ActBase extends AppCompatActivity
{
    protected DaysDatabase database;
    protected TimetableDatabase timetableDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        database = DaysDatabase.getInstance(this);
        timetableDatabase = TimetableDatabase.getInstance(this);
    }
}
