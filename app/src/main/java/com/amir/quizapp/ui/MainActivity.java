package com.amir.quizapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.amir.quizapp.R;
import com.amir.quizapp.ui.adapter.MainViewPagerAdapter;
import com.amir.quizapp.ui.main.MainViewModel;
import com.amir.quizapp.ui.viewpager.MainViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainViewModel viewModel;
    private BottomNavigationView bottomNavigationView;
    private MainViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        viewModel = new MainViewModel();
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager = findViewById(R.id.main_pager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_nav:
                viewPager.setCurrentItem(0);
                break;
            case R.id.history_nav:
                viewPager.setCurrentItem(1);
                break;
            case R.id.settings_nav:
                viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }
}