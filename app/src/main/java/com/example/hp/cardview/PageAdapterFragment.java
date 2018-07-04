package com.example.hp.cardview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

public class PageAdapterFragment extends FragmentPagerAdapter {

    private Context context;
    int no=3;

    public PageAdapterFragment(Context context, FragmentManager fm){

        super(fm);
        this.no = no;
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                Fragment1 f1 = new Fragment1();
                return f1;

            case 1:
                Fragment2 f2 = new Fragment2();
                return f2;

            case 2:
                Fragment3 f3 = new Fragment3();
                return f3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title =  "ONGOING";
                break;
            case 1:
                title =  "COMPLETED";
                break;
            case 2:
                title = "ENQUE";
                break;
            default:
                return null;
        }
        return title;
    }
}
