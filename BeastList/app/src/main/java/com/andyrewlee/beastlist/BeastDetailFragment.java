package com.andyrewlee.beastlist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by dev1 on 11/16/15.
 */
public class BeastDetailFragment extends Fragment {
    private Beast beast;
    private EditText objectiveEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beast = new Beast();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beast_detail, container, false);
        objectiveEditText = (EditText) view.findViewById(R.id.beast_objective);

        objectiveEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                beast.setObjective(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
