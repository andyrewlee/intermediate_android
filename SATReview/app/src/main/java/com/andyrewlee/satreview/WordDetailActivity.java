package com.andyrewlee.satreview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

public class WordDetailActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    public static Intent newIntent(Context packageContext, String currentWord) {
        Intent intent = new Intent(packageContext, WordDetailActivity.class);
        intent.putExtra("currentWord", currentWord);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String currentWord = (String) getIntent().getSerializableExtra("currentWord");
        return WordDetailFragment.newInstance(currentWord);
    }
}
