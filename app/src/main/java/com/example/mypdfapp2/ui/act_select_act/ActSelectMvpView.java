package com.example.mypdfapp2.ui.act_select_act;

import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.dialogs.DialogConfirm;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;
import com.example.mypdfapp2.databinding.ActSelectActBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActSelectMvpView extends ActBaseMvpView implements ActSelectMvp.MvpView
{
    private ActSelectMvp.Presenter presenter;
    private ActSelectActBinding binding;

    public ActSelectMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_select_act, null, false);
        rootView = binding.getRoot();
        binding.btnEditDay.setOnClickListener(v -> {
            presenter.btnEditClicked();
        });
        binding.btnDeleteDay.setOnClickListener(v -> {
            presenter.btnDeleteClicked();
        });
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActSelectMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }
}
