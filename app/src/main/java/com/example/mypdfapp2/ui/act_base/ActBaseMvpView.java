package com.example.mypdfapp2.ui.act_base;

import android.view.LayoutInflater;
import android.view.View;

public class ActBaseMvpView
{
    protected View rootView;
    protected LayoutInflater inflater;

    public ActBaseMvpView(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }
}
