package com.example.mypdfapp2.ui.act_base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mypdfapp2.data.AppDatabase;

public class ActBase extends AppCompatActivity
{
    protected AppDatabase database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getInstance(this);
    }
}
