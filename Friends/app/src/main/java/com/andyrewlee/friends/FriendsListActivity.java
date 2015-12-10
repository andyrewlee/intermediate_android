package com.andyrewlee.friends;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dev1 on 12/1/15.
 */
public class FriendsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new FriendsListFragment();
    }
}
