package com.okwy.practiceproject.TabNavigation.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.okwy.practiceproject.TabNavigation.Fragments.FirstTabFragment;
import com.okwy.practiceproject.TabNavigation.Fragments.SecondTabFragment;
import com.okwy.practiceproject.TabNavigation.Fragments.ThirdTabFragment;

import java.util.ArrayList;
import java.util.List;

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





//public class TabLayoutAdapter extends FragmentStatePagerAdapter {
//
//    private final List<Fragment> mFragmentList = new ArrayList<>();
//    private final List<String> mFragmentTitleList = new ArrayList<>();
//    private Context context;
//
//    public TabLayoutAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//
//    public TabLayoutAdapter(FragmentManager manager, Context mContext) {
//        super(manager);
//        this.context = mContext;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//
//    public void addFragment(Fragment fragment, String title) {
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
//    }
//
//
//    @Override
//    public int getCount() {
//        return mFragmentList.size();
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position);
//    }
//
//
//}
