package com.example.mypdfapp2.models;

import android.util.Log;
import android.widget.Toast;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "timetables")
public class ModelTimetable implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private long id;
    private int month = 0;
    private int year = 0;
    @Ignore
    private List<ModelDay> timetable;

    public ModelTimetable(long id, int month, int year)
    {
        this.id = id;
        this.month = month;
        this.year = year;

    }

    @Ignore
    public ModelTimetable(int month, int year)
    {
        this.month = month;
        this.year = year;

    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int timetableMonth)
    {
        this.month = timetableMonth;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int timetableYear)
    {
        this.year = timetableYear;
    }

    public List<ModelDay> getTimetable()
    {
        return timetable;
    }

    public void setTimetable(List<ModelDay> timetable)
    {
        this.timetable = timetable;
    }


    public boolean compareDates(ModelTimetable newTimetable, ModelTimetable timetable)
    {
        boolean isEarlier;

        if (timetable.getYear() < newTimetable.getYear())
        {
            isEarlier = true;
            Log.e("TAG", "compareDates: 1 - " + timetable.getYear() + newTimetable.getYear());
        }
        else if (timetable.getYear() == newTimetable.getYear() && timetable.getMonth() < newTimetable.getMonth())
        {
            isEarlier = true;
            Log.e("TAG", "compareDates: 1 - " + timetable.getYear() + timetable.getMonth() + newTimetable.getMonth());
        }
        else if (timetable.getYear() == newTimetable.getYear() && timetable.getMonth() == newTimetable.getMonth())
        {
            isEarlier = false;
            Log.e("TAG", "compareDates: 2 - " + timetable.getYear() + newTimetable.getMonth());
        }

        else
        {
            isEarlier = false;
            Log.e("TAG", "compareDates: 3 - ");
        }

        return isEarlier;

    }
}
