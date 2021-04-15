package com.example.mypdfapp2.ui.act_base;

import android.view.View;

public interface ActBaseMvp<PresenterType>
{
    View getRootView();

    void registerPresenter(PresenterType presenter);
}
