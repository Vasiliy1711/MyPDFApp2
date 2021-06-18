package com.example.mypdfapp2.ui.act_confirm;

import com.example.mypdfapp2.ui.act_base.ActBaseMvp;

public interface ActConfirmMvp
{
    interface MvpView extends ActBaseMvp<Presenter>
    {
        void setNewText();
    }

    interface Presenter
    {
        void btnUndoClicked();
        void btnDeleteClicked();
    }
}
