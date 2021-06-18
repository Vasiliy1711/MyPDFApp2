package com.example.mypdfapp2.dialogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.databinding.LaDialogConfirmBinding;
import com.example.mypdfapp2.ui.act_select_act.ActSelect;
import com.example.mypdfapp2.ui.act_timetable.ActTimetable;
import com.example.mypdfapp2.ui.act_timetable_day_add.ActTimetableDayAdd;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogConfirm extends BottomSheetDialogFragment
{
    public LaDialogConfirmBinding binding;
    DialogInterface.OnClickListener onClickListener;

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.la_dialog_confirm, container, false);


        binding.btnUndo.setOnClickListener(v ->
        {

            Intent intent = new Intent(getContext(), ActTimetable.class);
            startActivity(intent);

        });
        binding.btnDelete.setOnClickListener(v ->
        {

        });
        return binding.getRoot();
    }


}
