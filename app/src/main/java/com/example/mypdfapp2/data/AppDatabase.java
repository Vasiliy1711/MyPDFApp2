package com.example.mypdfapp2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mypdfapp2.models.ModelDay;
import com.example.mypdfapp2.models.ModelTimetable;

@Database(entities = {ModelDay.class, ModelTimetable.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static final String DB_NAME = "days_db";
    private static AppDatabase database;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context)
    {
        synchronized (LOCK)
        {
            if (database == null)
            {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return database;
    }

    public abstract DayDao dayDao();
    public abstract TimetableDao timetableDao();
}
