package com.example.bookshelf.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bookshelf.R;
import com.example.bookshelf.ui.main.fragments.BookmarkFragment;
import com.example.bookshelf.ui.main.fragments.HistoryFragment;
import com.example.bookshelf.ui.main.fragments.NewBooksFragment;
import com.example.bookshelf.ui.main.fragments.SearchFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.new_books, R.string.search, R.string.bookmark, R.string.history};
    private static final int TABS_COUNT = 4;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            NewBooksFragment instance = NewBooksFragment.getInstance();
            if (instance != null){
                return instance;
            }
            return NewBooksFragment.newInstance();
        }
        else if (position == 1){
            return SearchFragment.newInstance();
        }
        else if (position == 2){
            return BookmarkFragment.newInstance();
        }
        else{
            return HistoryFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }
}