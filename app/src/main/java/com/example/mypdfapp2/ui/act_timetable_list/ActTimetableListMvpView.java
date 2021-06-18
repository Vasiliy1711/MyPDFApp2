package com.example.mypdfapp2.ui.act_timetable_list;

import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.adapters.ModelTimetableAdapter;
import com.example.mypdfapp2.databinding.ActTimetableListBinding;
import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;
import com.example.mypdfapp2.ui.act_timetable.ActTimetableMvpView;

import java.util.List;

public class ActTimetableListMvpView extends ActBaseMvpView implements ActTimetableListMvp.MvpView
{
    private ActTimetableListMvp.Presenter presenter;
    private ActTimetableListBinding binding;
    private ModelTimetableAdapter adapter;

    public ActTimetableListMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_timetable_list, null, false);
        rootView = binding.getRoot();
        binding.revForTimetableList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapter = new ModelTimetableAdapter();
        binding.revForTimetableList.setAdapter(adapter);
        binding.fabAddTimetable.setOnClickListener(v -> presenter.fabAddTimetableClicked());
        adapter.setOnItemTimetableNameClickListener(position -> presenter.itemTimetableNameClicked(position));
    }

    @Override
    public void setActTimetableList(List<ModelTimetable> timetableList)
    {
        adapter.setTimetableList(timetableList);
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActTimetableListMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }
}
