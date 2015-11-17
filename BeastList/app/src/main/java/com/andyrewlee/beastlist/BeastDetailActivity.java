package com.andyrewlee.beastlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class BeastDetailActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BeastDetailFragment();
    }

    public static Intent newIntent(Context packageContext, UUID beastId) {
        Intent intent = new Intent(packageContext, BeastDetailActivity.class);
        intent.putExtra("beastId", beastId);
        return intent;
    }
}
