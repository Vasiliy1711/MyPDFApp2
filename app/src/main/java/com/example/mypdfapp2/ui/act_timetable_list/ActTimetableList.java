package com.example.mypdfapp2.ui.act_timetable_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.adapters.ModelTimetableAdapter;
import com.example.mypdfapp2.models.ModelTimetable;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_select_act.ActSelect;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.ui.act_timetable_add.ActTimetableAdd;

import java.util.ArrayList;
import java.util.List;

public class ActTimetableList extends ActBase implements ActTimetableListMvp.Presenter
{
    private ActTimetableListMvp.MvpView mvpView;
    private List<ModelTimetable> timetableList = new ArrayList<>();
    private ModelTimetableAdapter adapter = new ModelTimetableAdapter();
    private long timetableId;
    private final int REQUEST_CODE_AT = 1;
    private final int REQUEST_CODE_ATA = 2;
    private final int REQUEST_CODE_AS = 7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActTimetableListMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        setTimetableList();
    }

    @Override
    public void itemTimetableNameClicked(int position)
    {

        ModelTimetable timetable = adapter.getTimetableByPosition(timetableList, position);
        timetableId = timetable.getId();
        requestForSelect(timetableId);
    }

    @Override
    public void fabAddTimetableClicked()
    {
        Intent intent = new Intent(this, ActTimetableAdd.class);
        startActivityForResult(intent, REQUEST_CODE_ATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_CODE_ATA:
                {
                    long timetableId = data.getLongExtra("timetableId", -1);
                    sendRequest(timetableId);
                    break;
                }
                case REQUEST_CODE_AT:
                {
                    setTimetableList();
                    break;
                }
                case REQUEST_CODE_AS:
                {
                    boolean isDelete = data.getBooleanExtra("isDelete", false);
                    if (!isDelete)
                    {
                        sendRequest(timetableId);
                    }
                    else
                    {
                        database.timetableDao().deleteTimetable(database.timetableDao().getTimetableByID(timetableId));
                        setTimetableList();
                    }
                }
            }
        }
    }

    private void setTimetableList()
    {
//        database.timetableDao().deleteAllTimetables();
        timetableList = database.timetableDao().getAllTimetables();
        mvpView.setActTimetableList(timetableList);
    }

    private void sendRequest(long timetableId)
    {
        Intent intent = new Intent(this, ActTimetable.class);
        intent.putExtra("timetableId", timetableId);
        startActivityForResult(intent, REQUEST_CODE_AT);
    }

    private void requestForSelect(long timetableId)
    {
        Intent intent = new Intent(this, ActSelect.class);
        intent.putExtra("selected_timetableId", timetableId);
        startActivityForResult(intent, REQUEST_CODE_AS);
    }
}
