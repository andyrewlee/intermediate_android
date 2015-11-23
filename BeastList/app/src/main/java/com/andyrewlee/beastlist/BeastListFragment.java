package com.andyrewlee.beastlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 11/16/15.
 */
public class BeastListFragment extends Fragment {
    private RecyclerView beastListRecyclerView;
    private BeastAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_beast:
                Beast beast = new Beast();
                BeastsFactory.get(getActivity()).addBeast(beast);
                Intent intent = BeastPagerActivity.newIntent(getActivity(), beast.getId());
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beast_list, container, false);
        beastListRecyclerView = (RecyclerView) view.findViewById(R.id.beast_list);
        beastListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_beast_list, menu);
    }

    private void updateUI() {
        BeastsFactory beastsFactory = BeastsFactory.get(getActivity());
        List<Beast> beasts = beastsFactory.all();

        if(adapter == null) {
            adapter = new BeastAdapter(beasts);
            beastListRecyclerView.setAdapter(adapter);
        } else {
            adapter.setBeasts(beasts);
            adapter.notifyDataSetChanged();
        }
    }

    private class BeastHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TextView objectiveTextView;
        private TextView createdAtTextView;
        private CheckBox beastedCheckBox;

        private Beast beast;

        public BeastHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            objectiveTextView = (TextView) itemView.findViewById(R.id.cell_beast_objective);
            createdAtTextView = (TextView) itemView.findViewById(R.id.cell_beast_created_at);
            beastedCheckBox = (CheckBox) itemView.findViewById(R.id.cell_beasted_check_box);

            beastedCheckBox.setOnCheckedChangeListener(this);
        }

        public void bindBeast(Beast beast) {
            this.beast = beast;

            objectiveTextView.setText(beast.getObjective());
            createdAtTextView.setText(beast.getCreatedAt().toString());
            beastedCheckBox.setChecked(beast.isBeasted());
        }

        @Override
        public void onClick(View v) {
            Intent intent = BeastPagerActivity.newIntent(getActivity(), beast.getId());
            startActivity(intent);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            beast.setBeasted(isChecked);
            BeastsFactory.get(getActivity()).updateBeast(beast);
        }
    }

    private class BeastAdapter extends RecyclerView.Adapter<BeastHolder> {
        private List<Beast> beasts;

        public BeastAdapter(List<Beast> beasts) {
            this.beasts = beasts;
        }

        @Override
        public BeastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.cell_beast, parent, false);
            return new BeastHolder(view);
        }

        @Override
        public void onBindViewHolder(BeastHolder holder, int position) {
            Beast beast = beasts.get(position);
            holder.bindBeast(beast);
        }

        @Override
        public int getItemCount() {
            return beasts.size();
        }

        public void setBeasts(List<Beast> beasts) {
            this.beasts = beasts;
        }
    }
}
