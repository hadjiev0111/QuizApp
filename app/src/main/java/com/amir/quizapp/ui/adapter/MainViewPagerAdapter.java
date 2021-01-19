package com.amir.quizapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.amir.quizapp.ui.fragments.history.HistoryFragment;
import com.amir.quizapp.ui.fragments.main.MainFragment;
import com.amir.quizapp.ui.fragments.settings.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {


    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new MainFragment();
            case 1 :
                return new HistoryFragment();
            default:
                return new SettingsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
