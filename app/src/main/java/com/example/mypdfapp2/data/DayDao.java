package com.example.mypdfapp2.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mypdfapp2.models.ModelDay;

import java.util.List;

@Dao
public interface DayDao
{
    @Query("SELECT * FROM days")
    List<ModelDay> getAllDays();

    @Query("DELETE FROM days")
    void deleteAllDAys();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDay(ModelDay day);

    @Query("SELECT * FROM days WHERE id == :dayId")
    ModelDay getDayById(int dayId);

    @Delete
    void deleteDay(ModelDay day);

    @Update
    void update(ModelDay day);
}
