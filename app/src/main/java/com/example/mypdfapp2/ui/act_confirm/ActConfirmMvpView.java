package com.example.mypdfapp2.ui.act_confirm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.databinding.ActConfirmBinding;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;

public class ActConfirmMvpView extends ActBaseMvpView implements ActConfirmMvp.MvpView
{
    private ActConfirmMvp.Presenter presenter;
    private ActConfirmBinding binding;

    public ActConfirmMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_confirm, null, false);
        rootView = binding.getRoot();
        binding.btnDelete.setOnClickListener(v -> {
            presenter.btnDeleteClicked();
        });
        binding.btnUndo.setOnClickListener(v -> {
            presenter.btnUndoClicked();
        });
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActConfirmMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setTimetable()
    {
        binding.tvQuestion.setText("Вы действительно хотите удалить это расписание?");
    }

    @Override
    public void setMonth()
    {
        binding.tvQuestion.setText("Вы действительно хотите удалить этот месяц?");
    }
}
