package com.amir.quizapp.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient {

    private static QuizApi quizApi;

    private QuizApiClient() {}

    public static QuizApi getInstance(){
        if (quizApi == null){
            quizApi = buildRetrofit();
        }
        return quizApi;
    }

    private static QuizApi buildRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://opentdb.com/").addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(QuizApi.class);
    }

}
