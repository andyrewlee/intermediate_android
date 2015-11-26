package com.andyrewlee.satreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dev1 on 11/25/15.
 */
public class WordsListFragment extends Fragment {
    private RecyclerView wordsListRecyclerView;
    private WordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_words_list, container, false);
        wordsListRecyclerView = (RecyclerView) view.findViewById(R.id.words_list);
        wordsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        WordsFactory wordsFactory = WordsFactory.get(getActivity());
        List<Word> words = wordsFactory.getWords();

        if(adapter == null) {
            adapter = new WordAdapter(words);
            wordsListRecyclerView.setAdapter(adapter);
        }
    }

    private class WordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView wordTextView;
        private Word currentWord;

        public WordHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            wordTextView = (TextView) itemView.findViewById(R.id.word);
        }

        public void bindWord(Word word) {
            this.currentWord = word;
            wordTextView.setText(word.getWord());
        }

        @Override
        public void onClick(View v) {
            Intent intent = WordPagerActivity.newIntent(getActivity(), currentWord.getWord());
            startActivity(intent);
        }
    }

    private class WordAdapter extends RecyclerView.Adapter<WordHolder> {
        private List<Word> words;

        public WordAdapter(List<Word> words) {
            this.words = words;
        }

        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.cell_word, parent, false);
            return new WordHolder(view);
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = words.get(position);
            holder.bindWord(word);
        }

        @Override
        public int getItemCount() {
            return words.size();
        }
    }
}
