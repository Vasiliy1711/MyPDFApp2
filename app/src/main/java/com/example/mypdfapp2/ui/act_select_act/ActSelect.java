package com.example.mypdfapp2.ui.act_select_act;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypdfapp2.dialogs.DialogConfirm;
import com.example.mypdfapp2.ui.act_base.ActBase;
import com.example.mypdfapp2.ui.act_confirm.ActConfirm;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.ui.act_timetable_day_add.ActTimetableDayAdd;
import com.example.mypdfapp2.models.ModelDay;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActSelect extends ActBase implements ActSelectMvp.Presenter
{
    private ActSelectMvp.MvpView mvpView;
    private long dayId;
    private final int REQUEST_CODE_DELETE = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActSelectMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("selected_dayId"))
        {
            dayId = intent.getLongExtra("selected_dayId", 0);
        }
    }

    @Override
    public void btnEditClicked()
    {
        Toast.makeText(this, "Этот день будет изменен!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        boolean isDelete = false;
        intent.putExtra("isDelete", isDelete);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void btnDeleteClicked()
    {
        Toast.makeText(this, "Этот день будет удален!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActConfirm.class);
        startActivityForResult(intent, REQUEST_CODE_DELETE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            boolean isDelete = data.getBooleanExtra("isDelete", false);
            if (isDelete)
            {
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }
}
