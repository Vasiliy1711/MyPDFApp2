package com.example.mypdfapp2.ui.act_confirm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActConfirm extends AppCompatActivity implements ActConfirmMvp.Presenter
{
    private ActConfirmMvp.MvpView mvpView;
    private boolean isDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new ActConfirmMvpView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent.hasExtra("isDeleteAll"))
        {
            mvpView.setTimetable();
        }
        else if (intent.hasExtra("isDeleteMonth"))
        {
            mvpView.setMonth();
        }
    }

    @Override
    public void btnUndoClicked()
    {
        isDelete = false;
        sendReplay(isDelete);
    }

    @Override
    public void btnDeleteClicked()
    {
        isDelete = true;
        sendReplay(isDelete);
    }

    private void sendReplay(boolean isDelete)
    {
        Intent intent = new Intent();
        intent.putExtra("isDelete", isDelete);
        setResult(RESULT_OK, intent);
        finish();
    }
}
