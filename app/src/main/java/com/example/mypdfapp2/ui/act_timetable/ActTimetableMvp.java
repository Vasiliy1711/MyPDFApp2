package com.example.mypdfapp2.ui.act_timetable;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;
import com.example.mypdfapp2.models.ModelDay;

import java.util.List;

public interface ActTimetableMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        void setDays(List<ModelDay> days);
        void setMonth(String month);
        void setYear(String year);
    }

    interface Presenter
    {
        void fabAddDayClicked();
        void fabDeleteAllClicked();
        void fabSaveAllClicked();
        void itemDayClicked(int position);
    }
}
