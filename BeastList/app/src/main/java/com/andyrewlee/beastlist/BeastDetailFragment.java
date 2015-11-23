package com.andyrewlee.beastlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by dev1 on 11/16/15.
 */
public class BeastDetailFragment extends Fragment {
    private Beast beast;
    private EditText objectiveEditText;
    private TextView createdAtTextView;
    private CheckBox beastedCheckBox;
    private Button deleteBeastButton;

    public static BeastDetailFragment newInstance(UUID beastId) {
        Bundle args = new Bundle();
        args.putSerializable("beastId", beastId);

        BeastDetailFragment fragment = new BeastDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID beastId = (UUID) getArguments().getSerializable("beastId");
        beast = BeastsFactory.get(getActivity()).find(beastId);
    }

    @Override
    public void onPause() {
        super.onPause();

        BeastsFactory.get(getActivity()).updateBeast(beast);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beast_detail, container, false);
        objectiveEditText = (EditText) view.findViewById(R.id.beast_objective);
        objectiveEditText.setText(beast.getObjective());

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

        createdAtTextView = (TextView) view.findViewById(R.id.beast_created_at);
        createdAtTextView.setText(beast.getCreatedAt().toString());

        beastedCheckBox = (CheckBox) view.findViewById(R.id.beasted);
        beastedCheckBox.setChecked(beast.isBeasted());

        beastedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                beast.setBeasted(isChecked);
            }
        });

        deleteBeastButton = (Button) view.findViewById(R.id.delete_beast);

        deleteBeastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeastsFactory.get(getActivity()).destroy(beast);
                getActivity().finish();
            }
        });

        return view;
    }
}