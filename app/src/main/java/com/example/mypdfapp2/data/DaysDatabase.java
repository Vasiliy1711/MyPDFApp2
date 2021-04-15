package com.example.mypdfapp2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mypdfapp2.models.ModelDay;

@Database(entities = {ModelDay.class}, version = 3, exportSchema = false)
public abstract class DaysDatabase extends RoomDatabase
{
    private static final String DB_NAME = "days_db";
    private static DaysDatabase database;
    private static final Object LOCK = new Object();

    public static DaysDatabase getInstance(Context context)
    {
        synchronized (LOCK)
        {
            if (database == null)
            {
                database = Room.databaseBuilder(context, DaysDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return database;
    }

    public abstract DayDao dayDao();
}
