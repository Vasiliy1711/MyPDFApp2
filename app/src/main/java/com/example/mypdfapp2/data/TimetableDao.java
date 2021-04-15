package com.example.mypdfapp2.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mypdfapp2.models.ModelTimetable;

import java.util.List;

@Dao
public interface TimetableDao
{
    @Query("SELECT * FROM timetables")
    List<ModelTimetable> getAllTimetables();

    @Query("DELETE FROM timetables")
    void deleteAllTimetables();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimetable(ModelTimetable timetable);

    @Query("SELECT * FROM timetables WHERE id == :timetableId")
    ModelTimetable getTimetableByID(int timetableId);

    @Delete
    void deleteTimetable(ModelTimetable timetable);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTimetable(ModelTimetable timetable);
}
