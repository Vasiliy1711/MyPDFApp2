package com.example.mypdfapp2.ui.act_timetable_day_add;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;
import com.example.mypdfapp2.models.ModelDay;

public interface ActTimetableDayAddMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        ModelDay getNewDay();
        void setEditingDay(ModelDay day);
        String[] getEditingStrings();

    }

    interface Presenter
    {
        void btnAddDayClicked();
        void btnEditDayClicked();
    }
}
