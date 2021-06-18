package com.example.mypdfapp2.ui.act_timetable_add;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;

public interface ActTimetableAddMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        String getMonth();
        String getYear();
    }

    interface Presenter
    {
        void addTimetableNameClicked();
    }
}
