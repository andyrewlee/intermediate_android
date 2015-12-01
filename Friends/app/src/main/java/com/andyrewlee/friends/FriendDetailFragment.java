package com.andyrewlee.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dev1 on 11/30/15.
 */
// make sure we are importing the support Fragment
public class FriendDetailFragment extends Fragment {
    // this is not where we inflate our views and add event listeners
    // we will be using this method to load up some initial data later
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // this is where we inflate our views and add event listeners
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // we have not created this view yet
        View view = inflater.inflate(R.layout.fragment_friend_detail, container, false);
        return view;
    }
}
