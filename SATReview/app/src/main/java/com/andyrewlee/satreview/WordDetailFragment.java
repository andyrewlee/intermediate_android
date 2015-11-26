package com.andyrewlee.satreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dev1 on 11/25/15.
 */
public class WordDetailFragment extends Fragment {
    private Word currentWord;
    private TextView currentWordTextView;
    private TextView currentDefinitionTextView;
    private EditText currentNotes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WordsFactory wordsFactory = WordsFactory.get(getActivity());
        String currentWord = (String) getArguments().getSerializable("currentWord");
        this.currentWord = wordsFactory.getWord(currentWord);
    }

    public static WordDetailFragment newInstance(String currentWord) {
        Bundle args = new Bundle();
        args.putSerializable("currentWord", currentWord);

        WordDetailFragment fragment = new WordDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_word_detail, container, false);
        currentWordTextView = (TextView) view.findViewById(R.id.current_word);
        currentDefinitionTextView = (TextView) view.findViewById(R.id.current_definition);
        currentNotes = (EditText) view.findViewById(R.id.current_notes);

        currentWordTextView.setText(this.currentWord.getWord());
        currentDefinitionTextView.setText(this.currentWord.getDefinition());
        currentNotes.setText(this.currentWord.getNotes());

        currentNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentWord.setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
