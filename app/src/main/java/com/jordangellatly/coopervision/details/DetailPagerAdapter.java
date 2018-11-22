package com.jordangellatly.coopervision.details;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] {"Details", "Edit"};
    private Context context;

    public DetailPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailFragment.Companion.newInstance("Detail Fragment", 0);
            case 1:
                return EditFragment.newInstance("Edit Fragment", 1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
