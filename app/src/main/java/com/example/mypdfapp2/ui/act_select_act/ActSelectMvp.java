package com.example.mypdfapp2.ui.act_select_act;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;

public interface ActSelectMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {

    }

    interface Presenter
    {
        void btnEditClicked();
        void btnDeleteClicked();
    }
}
