package com.andyrewlee.beastlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 11/17/15.
 */
public class BeastPagerActivity extends AppCompatActivity {
    private static final String BEAST_ID = "com.andyrewlee.beastlist.beast_id";

    private ViewPager viewPager;
    private List<Beast> beasts;

    public static Intent newIntent(Context packageContext, UUID beastId) {
        Intent intent = new Intent(packageContext, BeastPagerActivity.class);
        intent.putExtra(BEAST_ID, beastId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beast_pager);

        UUID beastId = (UUID) getIntent().getSerializableExtra(BEAST_ID);

        viewPager = (ViewPager) findViewById(R.id.beast_view_pager);

        // model
        beasts = BeastsFactory.get(this).all();

        // need fragment manager to create fragment pager adapter
        FragmentManager fragmentManager = getSupportFragmentManager();

        // fragment pager adapter required for view pagers
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Beast beast = beasts.get(position);
                return BeastDetailFragment.newInstance(beast.getId());
            }

            @Override
            public int getCount() {
                return beasts.size();
            }
        });

        for(int i = 0; i < beasts.size(); i++) {
            if(beasts.get(i).getId().equals(beastId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
