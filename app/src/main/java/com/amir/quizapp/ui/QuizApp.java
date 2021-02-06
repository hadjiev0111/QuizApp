package com.amir.quizapp.ui;

import android.app.Application;

import androidx.room.Room;

import com.amir.quizapp.db.QuizDataBase;

public class QuizApp extends Application {
    private static QuizDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(),
                QuizDataBase.class, "database").allowMainThreadQueries().build();
    }

    public static QuizDataBase getDataBase() {
        return dataBase;
    }
}