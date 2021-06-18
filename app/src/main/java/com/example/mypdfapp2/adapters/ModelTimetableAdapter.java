package com.example.mypdfapp2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypdfapp2.R;
import com.example.mypdfapp2.converters.DateConverter;
import com.example.mypdfapp2.databinding.ItemTimetableNameBinding;
import com.example.mypdfapp2.models.ModelTimetable;

import java.util.ArrayList;
import java.util.List;

public class ModelTimetableAdapter extends RecyclerView.Adapter<ModelTimetableAdapter.ModelTimetableViewHolder>
{
    private OnItemTimetableNameClickListener onItemTimetableNameClickListener;

    public void setOnItemTimetableNameClickListener(OnItemTimetableNameClickListener onItemTimetableNameClickListener)
    {
        this.onItemTimetableNameClickListener = onItemTimetableNameClickListener;
    }

    public interface OnItemTimetableNameClickListener
    {
        void onItemTimetableNameClicked(int position);
    }

    private List<ModelTimetable> timetableList;

    public ModelTimetableAdapter()
    {
        timetableList = new ArrayList<>();
    }


    @NonNull
    @Override
    public ModelTimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable_name, parent, false);
        return new ModelTimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelTimetableViewHolder holder, int position)
    {
        ModelTimetable timetable = timetableList.get(position);
        holder.bindTimetable(timetable);
    }

    @Override
    public int getItemCount()
    {
//        if (timetableList == null)
//        {
//            return 0;
//        }
        return timetableList.size();

    }

    public void setTimetableList(List<ModelTimetable> timetableList)
    {
        this.timetableList = timetableList;
        notifyDataSetChanged();
    }

    public ModelTimetable getTimetableByPosition(List<ModelTimetable> timetableList, int position)
    {
        ModelTimetable timetable = timetableList.get(position);
        return timetable;
    }

    public void setTimetableByPosition(ModelTimetable timetable, int position)
    {
        timetableList.set(position,timetable);
    }

    class ModelTimetableViewHolder extends RecyclerView.ViewHolder
    {
        private TextView month;
        private TextView year;

        public ModelTimetableViewHolder(@NonNull View itemView)
        {
            super(itemView);
            month = itemView.findViewById(R.id.tv_month);
            year = itemView.findViewById(R.id.tv_year);
            itemView.setOnClickListener(v ->
            {
                if (onItemTimetableNameClickListener != null)
                {
                    onItemTimetableNameClickListener.onItemTimetableNameClicked(getAdapterPosition());
                }
            });
        }

        private void bindTimetable(ModelTimetable timetable)
        {
            this.month.setText(DateConverter.monthToString(timetable.getMonth()));
            this.year.setText(DateConverter.yearToString(timetable.getYear()));
        }
    }
}
