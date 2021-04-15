package com.example.mypdfapp2.ui.act_timetable_list;

import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBaseMvp;

import java.util.List;

public interface ActTimetableListMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        void setActTimetableList(List<ModelTimetable> timetableList);
    }

    interface Presenter
    {
        void itemTimetableNameClicked(int position);
        void fabAddTimetableClicked();
        void fabDeleteListClicked();
    }
}
