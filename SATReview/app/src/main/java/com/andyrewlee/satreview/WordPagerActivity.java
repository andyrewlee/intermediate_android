package com.andyrewlee.satreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by dev1 on 11/26/15.
 */
public class WordPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pager);
        String currentWord = (String) getIntent().getSerializableExtra("currentWord");

        viewPager = (ViewPager) findViewById(R.id.activity_word_pager_view_pager);
        words = WordsFactory.get(this).getWords();

        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Word word = words.get(position);
                return WordDetailFragment.newInstance(word.getWord());
            }

            @Override
            public int getCount() {
                return words.size();
            }
        });

        for(int i = 0; i < words.size(); i++) {
            if(words.get(i).getWord().equals(currentWord)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, String currentWord) {
        Intent intent = new Intent(packageContext, WordPagerActivity.class);
        intent.putExtra("currentWord", currentWord);
        return intent;
    }
}
