package com.amir.quizapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.amir.quizapp.data.models.QuizResult;

@Database(entities = {QuizResult.class}, version = 1)
    public abstract class QuizDataBase extends RoomDatabase {
        public abstract QuizDao quizDao();
}

