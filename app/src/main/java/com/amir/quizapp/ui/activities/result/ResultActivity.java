package com.amir.quizapp.ui.activities.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.amir.quizapp.databinding.ActivityResultBinding;
import com.amir.quizapp.ui.QuizApp;
import com.amir.quizapp.R;
import com.amir.quizapp.data.models.QuizResult;
import com.amir.quizapp.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private int correctAnswersCount;
    private int amount;
    private String difficulty ;
    private String category ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        amount = getIntent().getIntExtra("questionsAmount", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        category = getIntent().getStringExtra("category");
        binding.resultCategory.setText(category);
        binding.resultDifficultyValue.setText(difficulty);
        binding.resultCorrectAnswersValue.setText(String.valueOf(correctAnswersCount));
        binding.questionsAmountResult.setText("/"+ amount);
        binding.resultPercentValue.setText(getResultPercent(correctAnswersCount, amount)+"%");
        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizResult result = new QuizResult();
                result.setAmount(amount);
                result.setCategory(category);
                result.setCorrectAnswers(correctAnswersCount);
                result.setDifficulty(difficulty);
                result.setCreatedAt(System.currentTimeMillis());
                result.setResult(getResultPercent(correctAnswersCount, amount));
                QuizApp.getDataBase().quizDao().addResult(result);
                finish();
            }
        });
    }

    private int getResultPercent(int correctAnswersCount, int amount){
        int result = (correctAnswersCount*100)/(amount*100);
        return result;
    }
}