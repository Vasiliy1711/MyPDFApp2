package com.example.mypdfapp2.ui.act_timetable_day_add;

import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.ui.act_base.ActBaseMvpView;
import com.example.mypdfapp2.databinding.ActTimetableDayAddBinding;
import com.example.mypdfapp2.models.ModelDay;

public class ActTimetableDayAddMvpView extends ActBaseMvpView implements ActTimetableDayAddMvp.MvpView
{
    private ActTimetableDayAddMvp.Presenter presenter;
    private ActTimetableDayAddBinding binding;


    public ActTimetableDayAddMvpView(LayoutInflater inflater)
    {
        super(inflater);
        binding = DataBindingUtil.inflate(inflater, R.layout.act_timetable_day_add, null, false);
        rootView = binding.getRoot();
        setListeners();
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(ActTimetableDayAddMvp.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public ModelDay getNewDay()
    {
        String day = binding.etWeekDay.getText().toString().trim();
        String date = binding.etDate.getText().toString().trim();
        String holiday = binding.etHoliday.getText().toString().trim();
        String service = binding.etDivineService.getText().toString().trim();
        String time = binding.etTime.getText().toString().trim();
        ModelDay newDay = new ModelDay(day, date, holiday, service, time);
        return newDay;
    }

    @Override
    public void setEditingDay(ModelDay day)
    {
        binding.etWeekDay.setText(day.getDay());
        binding.etDate.setText(day.getDate());
        binding.etHoliday.setText(day.getHoliday());
        binding.etDivineService.setText(day.getService());
        binding.etTime.setText(day.getTime());
        binding.btnAddDay.setText("Сохранить изменения");
        setListeners();
    }

    @Override
    public String[] getEditingStrings()
    {
        String day = binding.etWeekDay.getText().toString().trim();
        String date = binding.etDate.getText().toString().trim();
        String holiday = binding.etHoliday.getText().toString().trim();
        String service = binding.etDivineService.getText().toString().trim();
        String time = binding.etTime.getText().toString().trim();
        String[] strings = new String[]{day, date, holiday, service, time};
        return strings;
    }


    private void setListeners()
    {
        String btnText = binding.btnAddDay.getText().toString();
        boolean isBtnAdd = btnText.equals("Добавить");
        binding.btnAddDay.setOnClickListener(v ->
        {
            if (isBtnAdd)
            {
                presenter.btnAddDayClicked();
            } else
            {
                presenter.btnEditDayClicked();
            }
        });
    }
}
