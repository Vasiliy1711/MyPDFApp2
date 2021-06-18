package com.example.mypdfapp2.ui.act_timetable_add;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.databinding.ActTimetableAddBinding;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;

public class ActTimetableAddMvpView extends ActBaseMvpView implements ActTimetableAddMvp.MvpView
{
    private ActTimetableAddMvp.Presenter presenter;
    private ActTimetableAddBinding binding;

    public ActTimetableAddMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_timetable_add, null, false);
        rootView = binding.getRoot();
        binding.btnAddTimetableName.setOnClickListener(v -> presenter.addTimetableNameClicked());
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActTimetableAddMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public String getMonth()
    {
        String month = binding.etAddMonth.getText().toString().trim();
        return month;
    }

    @Override
    public String getYear()
    {
        String year = binding.etAddYear.getText().toString().trim();
        return year;
    }
}
