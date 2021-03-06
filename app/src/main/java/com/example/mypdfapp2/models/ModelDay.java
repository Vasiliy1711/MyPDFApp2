package com.example.mypdfapp2.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "days")
public class ModelDay implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long timetableId;
    private String day;
    private String date;
    private String holiday;
    private String service;
    private String time;

    public ModelDay(long id, long timetableId, String day, String date, String holiday, String service, String time)
    {
        this.id = id;
        this.timetableId = timetableId;
        this.day = day;
        this.date = date;
        this.holiday = holiday;
        this.service = service;
        this.time = time;
    }

    @Ignore
    public ModelDay( String day, String date, String holiday, String service, String time)
    {
        this.day = day;
        this.date = date;
        this.holiday = holiday;
        this.service = service;
        this.time = time;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getTimetableId()
    {
        return timetableId;
    }

    public void setTimetableId(long timetableId)
    {
        this.timetableId = timetableId;
    }

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getHoliday()
    {
        return holiday;
    }

    public void setHoliday(String holiday)
    {
        this.holiday = holiday;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
