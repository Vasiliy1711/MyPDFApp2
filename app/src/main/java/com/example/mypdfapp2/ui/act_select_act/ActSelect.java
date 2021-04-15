package com.example.mypdfapp2.ui.act_select_act;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.dialogs.DialogConfirm;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.ui.act_timetable_day_add.ActTimetableDayAdd;
import com.example.mypdfapp2.models.ModelDay;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActSelect extends ActBase implements ActSelectMvp.Presenter, DialogInterface.OnClickListener
{
    private ActSelectMvp.MvpView mvpView;
    private ModelDay day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActSelectMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("selected_day"))
        {
            day = (ModelDay) intent.getSerializableExtra("selected_day");
        }
    }

    @Override
    public void btnEditClicked()
    {
        Toast.makeText(this, "Этот день будет изменен!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActTimetableDayAdd.class);
        intent.putExtra("editing_day", day);
        startActivity(intent);
    }

    @Override
    public void btnDeleteClicked()
    {
        Toast.makeText(this, "Этот день будет удален!", Toast.LENGTH_SHORT).show();
        BottomSheetDialogFragment dialog = new DialogConfirm();
        dialog.show(getSupportFragmentManager(), null);


    }

    public void deleteDay()
    {
        database.dayDao().deleteDay(day);
        Intent intent = new Intent(this, ActTimetable.class);
        startActivity(intent);
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        deleteDay();
        dialog.dismiss();
    }
}
