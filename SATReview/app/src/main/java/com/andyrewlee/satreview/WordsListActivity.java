package com.andyrewlee.satreview;

import android.support.v4.app.Fragment;

/**
 * Created by dev1 on 11/25/15.
 */
public class WordsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WordsListFragment();
    }
}
