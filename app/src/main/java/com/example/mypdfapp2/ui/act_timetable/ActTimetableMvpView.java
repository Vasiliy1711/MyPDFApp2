package com.example.mypdfapp2.ui.act_timetable;

import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;
import com.example.mypdfapp2.adapters.ModelDayAdapter;
import com.example.mypdfapp2.databinding.ActTimetableBinding;
import com.example.mypdfapp2.models.ModelDay;

import java.util.List;

public class ActTimetableMvpView extends ActBaseMvpView implements ActTimetableMvp.MvpView
{
    private ActTimetableMvp.Presenter presenter;
    private ActTimetableBinding binding;
    private ModelDayAdapter adapter;

    public ActTimetableMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_timetable, null, false);
        rootView = binding.getRoot();
        binding.revForDays.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapter = new ModelDayAdapter();
        binding.revForDays.setAdapter(adapter);
        binding.fabAddDay.setOnClickListener(v -> presenter.fabAddDayClicked());
        binding.fabDeleteAll.setOnClickListener(v -> presenter.fabDeleteAllClicked());
        adapter.setOnItemDayClickListener(position -> presenter.itemDayClicked(position));
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActTimetableMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setDays(List<ModelDay> days)
    {
        adapter.setDays(days);
    }
}
