package com.andyrewlee.students;

import android.support.v4.app.Fragment;

/**
 * Created by dev1 on 11/24/15.
 */
public class StudentListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StudentListFragment();
    }
}
