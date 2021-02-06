package com.amir.quizapp.data.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.amir.quizapp.data.models.Question;
import com.amir.quizapp.data.models.QuizResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository  {

    private static QuizRepository instance;
    private MutableLiveData<List<Question>> questions;

    private QuizRepository() {
        questions = new MutableLiveData<>();
    }

    public static QuizRepository getInstance() {
        if (instance == null) {
            instance = new QuizRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Question>> loadQuestions(int id, int category, String difficulty) {
        List<Question> questionsList = new ArrayList<>();
        QuizApiClient.getInstance().getQuestions(id, category, difficulty).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        questionsList.addAll(response.body().getQuestions());
                        shuffleAnswers(questionsList);
                        questions.setValue(questionsList);
                        Log.e("Success", call.request().url().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.e("tag", "onFailure" + t.getMessage() + t.getLocalizedMessage());
            }
        });

        return questions;
    }

    private void shuffleAnswers(List<Question> questions) {
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).getIncorrectAnswers().add(questions.get(i).getCorrectAnswer());
            Collections.shuffle(questions.get(i).getIncorrectAnswers());
        }
    }
}
