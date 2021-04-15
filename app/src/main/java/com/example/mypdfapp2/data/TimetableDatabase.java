package com.example.mypdfapp2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mypdfapp2.models.ModelTimetable;

@Database(entities = {ModelTimetable.class}, version = 2, exportSchema = false)
public abstract class TimetableDatabase extends RoomDatabase
{
    private static final String DB_NAME = "timetables.db";
    private static TimetableDatabase db;
    private static final Object LOCK = new Object();

    public static TimetableDatabase getInstance(Context context)
    {
        synchronized (LOCK)
        {
            if (db == null)
            {
                db = Room.databaseBuilder(context, TimetableDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return db;
    }

    public abstract TimetableDao timetableDao();
}
