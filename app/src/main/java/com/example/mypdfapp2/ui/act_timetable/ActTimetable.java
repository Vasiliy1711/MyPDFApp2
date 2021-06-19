package com.example.mypdfapp2.ui.act_timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.builders.TimetablePdfBuilder;
import com.example.mypdfapp2.builders.TimetablePdfBuilder1;
import com.example.mypdfapp2.converters.DateConverter;
import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_confirm.ActConfirm;
import com.example.mypdfapp2.ui.act_select_act.ActSelect;
import com.example.mypdfapp2.ui.act_timetable_day_add.ActTimetableDayAdd;
import com.example.mypdfapp2.adapters.ModelDayAdapter;
import com.example.mypdfapp2.models.ModelDay;
import com.example.mypdfapp2.ui.act_timetable_list.ActTimetableList;

import java.util.ArrayList;
import java.util.List;

public class ActTimetable extends ActBase implements ActTimetableMvp.Presenter
{
    private ActTimetableMvp.MvpView mvpView;
    private List<ModelDay> days = new ArrayList<>();
    private ModelDayAdapter adapter = new ModelDayAdapter();
    private ModelTimetable timetable;
    private ModelDay day;
    private long timetableId;
    private final int REQUEST_CODE_SELECT = 3;
    private final int REQUEST_CODE_EDIT = 4;
    private final int REQUEST_CODE_ADD = 5;
    private final int REQUEST_CODE_DELETE = 6;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("timetableId"))
        {
            timetableId = intent.getLongExtra("timetableId", 0);
            initTimetable(timetableId);
        }
    }

    @Override
    public void fabAddDayClicked()
    {
        Intent intent = new Intent(this, ActTimetableDayAdd.class);
        long timetableId = timetable.getId();
        intent.putExtra("timetableId", timetableId);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    public void fabDeleteAllClicked()
    {
        Intent intent = new Intent(this, ActConfirm.class);
        boolean isDeleteAll = true;
        intent.putExtra("isDeleteAll", isDeleteAll);
        startActivityForResult(intent, REQUEST_CODE_DELETE);
    }

    @Override
    public void fabSaveAllClicked()
    {
        days = database.dayDao().getAllByTimetableId(timetable.getId());
        timetable.setTimetable(days);
        createPdf(timetable);
        Intent intentToList = new Intent();
        setResult(RESULT_OK, intentToList);
        finish();
    }

    @Override
    public void itemDayClicked(int position)
    {
        Intent intent = new Intent(this, ActSelect.class);
        day = adapter.getDayByPosition(days, position);
        intent.putExtra("selected_dayId", day.getId());
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_CODE_SELECT:
                {
                    if (!data.getBooleanExtra("isDelete", false))
                    {
                        long dayId = day.getId();
                        Intent intent = new Intent(this, ActTimetableDayAdd.class);
                        intent.putExtra("editing_dayId", dayId);
                        startActivityForResult(intent, REQUEST_CODE_EDIT);
                    }
                    else
                    {
                        database.dayDao().deleteDay(day);
                        initTimetable(timetable.getId());
                    }

                    break;
                }
                case REQUEST_CODE_ADD:
                case REQUEST_CODE_EDIT:
                {
                    initTimetable(data.getLongExtra("timetableId", -1));
                    break;
                }
                case REQUEST_CODE_DELETE:
                {
                    if (data.getBooleanExtra("isDelete", false))
                    {
                        database.dayDao().deleteAllDAysFromTimetable(timetableId);
                        initTimetable(timetableId);
                    }

                }
            }
        }
    }

    private void createPdf(ModelTimetable timetable)
    {
        String newMonth = DateConverter.monthToString(timetable.getMonth()) + " " + DateConverter.yearToString(timetable.getYear());
        TimetablePdfBuilder builder = new TimetablePdfBuilder();
        builder.createPdfFile(newMonth, timetable.getTimetable(), this);
    }

    private void initTimetable(long timetableId)
    {
        timetable = database.timetableDao().getTimetableByID(timetableId);
        days = database.dayDao().getAllByTimetableId(timetableId);
        timetable.setTimetable(days);
        mvpView.setDays(days);
        mvpView.setMonth(DateConverter.monthToString(timetable.getMonth()));
        mvpView.setYear(DateConverter.yearToString(timetable.getYear()));
    }
}
