package com.example.mypdfapp2.data;

import android.content.Context;

import com.example.mypdfapp2.models.ModelDay;
import com.example.mypdfapp2.models.ModelTimetable;

import java.util.List;

public class TimetableDaoHelper
{
    public static long saveTimetable(Context context, ModelTimetable timetable)
    {
        TimetableDao timetableDao = AppDatabase.getInstance(context).timetableDao();
        DayDao dayDao = AppDatabase.getInstance(context).dayDao();
        long id = timetableDao.insertTimetable(timetable);
        for (ModelDay modelDay : timetable.getTimetable())
        {
            modelDay.setTimetableId(id);
            dayDao.insertDay(modelDay);
        }
        return id;
    }

    public static ModelTimetable getTimetableById(Context context, long id)
    {
        TimetableDao timetableDao = AppDatabase.getInstance(context).timetableDao();
        DayDao dayDao = AppDatabase.getInstance(context).dayDao();
        List<ModelDay> days =  dayDao.getAllByTimetableId(id); // получаем все дни таблицы по Id
        ModelTimetable timetable = timetableDao.getTimetableByID(id); // получаем шапку таблицы
        timetable.setTimetable(days); // вставляем лист с днями в таблицу
        return timetable;
    }
}
