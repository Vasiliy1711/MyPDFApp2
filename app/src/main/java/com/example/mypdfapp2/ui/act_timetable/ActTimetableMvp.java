package com.example.mypdfapp2.ui.act_timetable;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;
import com.example.mypdfapp2.models.ModelDay;

import java.util.List;

public interface ActTimetableMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        void setDays(List<ModelDay> days);
    }

    interface Presenter
    {
        void fabAddDayClicked();
        void fabDeleteAllClicked();
        void itemDayClicked(int position);
    }
}