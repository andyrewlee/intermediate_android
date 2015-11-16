package com.andyrewlee.beastlist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeastDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beast_detail);
        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = new BeastDetailFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
