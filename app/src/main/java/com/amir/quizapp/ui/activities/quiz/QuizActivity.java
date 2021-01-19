package com.amir.quizapp.ui.activities.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.os.Bundle;
import com.amir.quizapp.R;
import com.amir.quizapp.databinding.ActivityQuizBinding;
import com.amir.quizapp.ui.adapter.QuizAdapter;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private QuizViewModel viewModel;
    private int amount;
    private int category;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.quizBackArrow.setOnClickListener(v -> finish());
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.setViewModel(viewModel);
        amount = getIntent().getIntExtra("amount", 0);
        category = getIntent().getIntExtra("categoryId", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        viewModel.getQuestions(amount, category, difficulty.toLowerCase());
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        QuizAdapter adapter = new QuizAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.quizRecycler.setLayoutManager(manager);
        binding.quizRecycler.setAdapter(adapter);
        viewModel.questionsLiveData.observe(this, adapter::addList);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.quizRecycler);
        binding.skipBtn.setOnClickListener(v -> {
            if (manager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                binding.quizRecycler.smoothScrollToPosition(manager.findLastCompletelyVisibleItemPosition() + 1);
            }
        });
    }
}