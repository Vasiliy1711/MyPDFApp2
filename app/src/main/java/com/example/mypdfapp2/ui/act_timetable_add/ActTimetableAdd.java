package com.example.mypdfapp2.ui.act_timetable_add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.converters.DateConverter;
import com.example.mypdfapp2.data.AppDatabase;
import com.example.mypdfapp2.data.TimetableDaoHelper;
import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.ui.act_timetable_list.ActTimetableList;

import java.util.ArrayList;
import java.util.List;

public class ActTimetableAdd extends ActBase implements ActTimetableAddMvp.Presenter
{
    private ActTimetableAddMvp.MvpView mvpView;
    private ModelTimetable timetable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableAddMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
    }

    @Override
    public void addTimetableNameClicked()
    {
        int month = DateConverter.monthToInt(mvpView.getMonth());
        int year = DateConverter.yearToInt(mvpView.getYear());

        if (month == 0)
        {
            Toast.makeText(this, "Поле \"Месяц\" должно быть заполнено!", Toast.LENGTH_SHORT).show();
        } else if (year == 0)
        {
            Toast.makeText(this, "Поле \"Год\" должно быть заполнено!", Toast.LENGTH_SHORT).show();
        } else
        {
            timetable = new ModelTimetable(month, year);
            Log.e("TAG", "timetable.getYear: " + timetable.getYear());
//            long timetableId = TimetableDaoHelper.saveTimetable(this, timetable);
            long timetableId = database.timetableDao().insertTimetable(timetable);
            Intent intent = new Intent();
            Log.e("TAG", "timetableId: " + timetableId);
            intent.putExtra("timetableId", timetableId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}










//    private void setTimetableAscending(ModelTimetable newTimetable)
//    {
//        List<ModelTimetable> timetables = database.timetableDao().getAllTimetables();
//        List<ModelTimetable> updatedTimetableList = new ArrayList<>();
//        for (int i = 0; i < timetables.size(); i++)
//        {
//            ModelTimetable timetable = timetables.get(i);
//            if (timetable.compareDates(newTimetable, timetable))
//            {
//                updatedTimetableList.add(timetable);
//            }
//            else if (updatedTimetableList.contains(newTimetable))
//            {
//                updatedTimetableList.add(timetable);
//            }
//            else
//            {
//                updatedTimetableList.add(newTimetable);
//            }
//        }
//        if (!updatedTimetableList.isEmpty())
//        {
//            database.timetableDao().deleteAllTimetables();
//            for (ModelTimetable timetable : updatedTimetableList)
//            {
//                database.timetableDao().insertTimetable(timetable);
//            }
//        }
//    }
//}
