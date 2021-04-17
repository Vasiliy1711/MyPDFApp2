package com.example.mypdfapp2.ui.act_timetable_list;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.adapters.ModelTimetableAdapter;
import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBase;

import java.util.ArrayList;
import java.util.List;

public class ActTimetableList extends ActBase implements ActTimetableListMvp.Presenter
{
    private ActTimetableListMvp.MvpView mvpView;
    private List<ModelTimetable> timetableList = new ArrayList<>();
    private ModelTimetableAdapter adapter = new ModelTimetableAdapter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableListMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
    }

    @Override
    public void itemTimetableNameClicked(int position)
    {
        timetableDatabase.timetableDao().getAllTimetables();
    }

    @Override
    public void fabAddTimetableClicked()
    {

    }

    @Override
    public void fabDeleteListClicked()
    {

    }
}
