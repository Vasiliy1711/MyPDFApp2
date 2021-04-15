package com.example.mypdfapp2.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "timetables")
public class ModelTimetable implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String month;
    private String year;

    public ModelTimetable(int id, String month, String year)
    {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    @Ignore
    public ModelTimetable(String month, String year)
    {
        this.month = month;
        this.year = year;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int timetableId)
    {
        this.id = timetableId;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String timetableMonth)
    {
        this.month = timetableMonth;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String timetableYear)
    {
        this.year = timetableYear;
    }
}
