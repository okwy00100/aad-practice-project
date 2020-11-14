package com.okwy.practiceproject.TabNavigation.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.okwy.practiceproject.TabNavigation.Fragments.FirstTabFragment;
import com.okwy.practiceproject.TabNavigation.Fragments.SecondTabFragment;
import com.okwy.practiceproject.TabNavigation.Fragments.ThirdTabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FirstTabFragment();
            case 1:
                return new SecondTabFragment();
            case 2:
                return new ThirdTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
