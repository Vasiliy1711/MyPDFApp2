package com.example.mypdfapp2.ui.act_timetable_day_add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.models.ModelDay;

public class ActTimetableDayAdd extends ActBase implements ActTimetableDayAddMvp.Presenter
{
    private ActTimetableDayAddMvp.MvpView mvpView;
    private ModelDay editingDay;
    private long timetableId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableDayAddMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("timetableId"))
        {
            timetableId = intent.getLongExtra("timetableId", 0);
        }
        if (intent.hasExtra("editing_dayId"))
        {
            long editing_dayId = intent.getLongExtra("editing_dayId", 0);
            editingDay = database.dayDao().getDayById(editing_dayId);
            mvpView.setEditingDay(editingDay);
        }
    }

    @Override
    public void btnAddDayClicked()
    {

        Intent intent = new Intent(this, ActTimetable.class);
        ModelDay day = mvpView.getNewDay();
        day.setTimetableId(timetableId);
        database.dayDao().insertDay(day);
        intent.putExtra("timetableId", timetableId);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void btnEditDayClicked()
    {
        database.dayDao().update(getEditingDay());
        Intent intent = new Intent();
        timetableId = editingDay.getTimetableId();
        intent.putExtra("timetableId", timetableId);
        setResult(RESULT_OK, intent);
        finish();
    }

    private ModelDay getEditingDay()
    {
        String[] strings = mvpView.getEditingStrings();
        editingDay.setDay(strings[0]);
        editingDay.setDate(strings[1]);
        editingDay.setHoliday(strings[2]);
        editingDay.setService(strings[3]);
        editingDay.setTime(strings[4]);
        return editingDay;
    }
}
