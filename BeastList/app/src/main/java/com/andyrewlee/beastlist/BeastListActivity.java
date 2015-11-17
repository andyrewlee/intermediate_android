package com.andyrewlee.beastlist;

import android.support.v4.app.Fragment;

/**
 * Created by dev1 on 11/16/15.
 */
public class BeastListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BeastListFragment();
    }
}
