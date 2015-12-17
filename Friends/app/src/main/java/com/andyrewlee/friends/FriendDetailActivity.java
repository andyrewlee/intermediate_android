package com.andyrewlee.friends;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class FriendDetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_FRIEND_ID = "com.codingdojo.friends.student_id";

    public static Intent newIntent(Context packageContext, UUID friendId) {
        Intent intent = new Intent(packageContext, FriendDetailActivity.class);
        intent.putExtra(EXTRA_FRIEND_ID, friendId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID friendId = (UUID) getIntent().getSerializableExtra(EXTRA_FRIEND_ID);
        return FriendDetailFragment.newInstance(friendId);
    }
}
