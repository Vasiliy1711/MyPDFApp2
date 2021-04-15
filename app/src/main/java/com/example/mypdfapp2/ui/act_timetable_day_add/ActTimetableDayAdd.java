package com.example.mypdfapp2.ui.act_timetable_day_add;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.models.ModelDay;

public class ActTimetableDayAdd extends ActBase implements ActTimetableDayAddMvp.Presenter
{
    private ActTimetableDayAddMvp.MvpView mvpView;
    ModelDay editingDay;
    boolean isEditMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableDayAddMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("editing_day"))
        {
            editingDay = (ModelDay) intent.getSerializableExtra("editing_day");
            mvpView.setEditingDay(editingDay);
            isEditMode = true;
        }
    }

    @Override
    public void btnAddDayClicked()
    {

        Intent intent = new Intent(this, ActTimetable.class);
        ModelDay day = mvpView.getNewDay();
        intent.putExtra("new_day", day);
        startActivity(intent);
    }

    @Override
    public void btnEditDayClicked()
    {
        database.dayDao().update(getEditingDay());
        Intent intent = new Intent(this, ActTimetable.class);
        intent.putExtra("new_day", editingDay);
        startActivity(intent);
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
