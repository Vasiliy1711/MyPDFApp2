package com.example.mypdfapp2.ui.act_timetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.builders.DayPdfBuilder;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_select_act.ActSelect;
import com.example.mypdfapp2.ui.act_timetable_day_add.ActTimetableDayAdd;
import com.example.mypdfapp2.adapters.ModelDayAdapter;
import com.example.mypdfapp2.models.ModelDay;

import java.util.ArrayList;
import java.util.List;

public class ActTimetable extends ActBase implements ActTimetableMvp.Presenter
{
    private ActTimetableMvp.MvpView mvpView;
    private List<ModelDay> days = new ArrayList<>();
    private ModelDayAdapter adapter = new ModelDayAdapter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        addDayToDatabase();
        bindDatabase();
        createPdf();
    }

    @Override
    public void fabAddDayClicked()
    {
        Intent intent = new Intent(this, ActTimetableDayAdd.class);
        startActivity(intent);
    }

    @Override
    public void fabDeleteAllClicked()
    {
        database.dayDao().deleteAllDAys();
        bindDatabase();
        createPdf();
    }

    @Override
    public void itemDayClicked(int position)
    {
        Intent intent = new Intent(this, ActSelect.class);
        ModelDay day = adapter.getDayByPosition(days, position);
        intent.putExtra("selected_day", day);
        startActivity(intent);
        Toast.makeText(this, "ItemDay" + " " + position + " " + "Clicked", Toast.LENGTH_SHORT).show();
    }

    private void createPdf()
    {
        DayPdfBuilder builder = new DayPdfBuilder();
        builder.createPdfFile(days, this);
    }

    private void bindDatabase()
    {
        days = database.dayDao().getAllDays();
        mvpView.setDays(days);
    }

    private void addDayToDatabase()
    {
        Intent intent = getIntent();
        if (intent.hasExtra("new_day"))
        {
            ModelDay day = (ModelDay) intent.getSerializableExtra("new_day");
            database.dayDao().insertDay(day);
        }
        bindDatabase();
    }
}
