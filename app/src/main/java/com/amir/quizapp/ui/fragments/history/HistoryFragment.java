package com.amir.quizapp.ui.fragments.history;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amir.quizapp.ui.QuizApp;
import com.amir.quizapp.R;
import com.amir.quizapp.data.models.QuizResult;
import com.amir.quizapp.databinding.HistoryFragmentBinding;
import com.amir.quizapp.ui.adapter.HistoryAdapter;


public class HistoryFragment extends Fragment implements HistoryAdapter.OnHistoryClick {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    HistoryAdapter historyAdapter = new HistoryAdapter(this);


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.historyRecycler.setAdapter(historyAdapter);
        QuizApp.getDataBase().quizDao().getHistoryList().observe(this, results -> historyAdapter.addList(results));
    }

    @Override
    public void onResume() {
        super.onResume();
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClick(int pos) {
        QuizApp.getDataBase().quizDao().delete(historyAdapter.getItem(pos));
        historyAdapter.deleteItem(pos);
    }
}