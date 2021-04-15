package com.example.mypdfapp2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.models.ModelDay;

import java.util.ArrayList;
import java.util.List;

public class ModelDayAdapter extends RecyclerView.Adapter<ModelDayAdapter.ModelDayViewHolder>
{
    private OnItemDayClickListener onItemDayClickListener;

    public void setOnItemDayClickListener(OnItemDayClickListener onItemDayClickListener)
    {
        this.onItemDayClickListener = onItemDayClickListener;
    }

    public interface OnItemDayClickListener
    {
        void onItemDayClicked(int position);
    }

    private List<ModelDay> days;

    public ModelDayAdapter()
    {
        days = new ArrayList<>();
    }

    public void setDays(List<ModelDay> days)
    {
        this.days = days;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ModelDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ModelDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelDayViewHolder holder, int position)
    {
        ModelDay day = days.get(position);
        holder.bindDay(day);
    }

    @Override
    public int getItemCount()
    {
        if (days == null)
        {
            return 0;
        }
        return days.size();
    }

    public ModelDay getDayByPosition(List<ModelDay> days, int position)
    {
        ModelDay day = days.get(position);
        return day;
    }


    class ModelDayViewHolder extends RecyclerView.ViewHolder
    {
        private TextView day;
        private TextView date;
        private TextView holiday;
        private TextView service;
        private TextView time;


        public ModelDayViewHolder(@NonNull View itemView)
        {
            super(itemView);
            day = itemView.findViewById(R.id.tv_day);
            date = itemView.findViewById(R.id.tv_date);
            holiday = itemView.findViewById(R.id.tv_holiday);
            service = itemView.findViewById(R.id.tv_service1);
            time = itemView.findViewById(R.id.tv_time1);
            itemView.setOnClickListener(v ->
            {
                if (onItemDayClickListener != null)
                {
                    onItemDayClickListener.onItemDayClicked(getAdapterPosition());
                }
            });
        }

        private void bindDay(ModelDay day)
        {
            this.day.setText(day.getDay());
            this.date.setText(day.getDate());
            this.holiday.setText(day.getHoliday());
            this.service.setText(day.getService());
            this.time.setText(day.getTime());
        }
    }
}
