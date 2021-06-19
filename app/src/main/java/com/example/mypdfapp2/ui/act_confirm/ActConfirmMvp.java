package com.example.mypdfapp2.ui.act_confirm;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;

public interface ActConfirmMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        void setTimetable();
        void setMonth();
    }

    interface Presenter
    {
        void btnUndoClicked();
        void btnDeleteClicked();
    }
}
