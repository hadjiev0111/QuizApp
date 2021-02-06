package com.amir.quizapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.amir.quizapp.data.models.QuizResult;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QuizDao {

    @Insert(onConflict = REPLACE)
    void addResult(QuizResult quizResult);

    @Delete
    void delete(QuizResult quizResult);

    @Query("DELETE FROM qresult")
    void deleteAll();

    @Query("SELECT * FROM qresult")
    LiveData<List<QuizResult>> getHistoryList();
}