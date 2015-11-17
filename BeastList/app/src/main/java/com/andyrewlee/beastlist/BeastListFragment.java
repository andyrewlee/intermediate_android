package com.andyrewlee.beastlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dev1 on 11/16/15.
 */
public class BeastListFragment extends Fragment {
    private RecyclerView beastListRecyclerView;
    private BeastAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beast_list, container, false);
        beastListRecyclerView = (RecyclerView) view.findViewById(R.id.beast_list);
        beastListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        List<Beast> beasts = Beast.all();
        adapter = new BeastAdapter(beasts);
        beastListRecyclerView.setAdapter(adapter);
    }

    private class BeastHolder extends RecyclerView.ViewHolder {
        public TextView objectiveTextView;

        public BeastHolder(View itemView) {
            super(itemView);
            objectiveTextView = (TextView) itemView;
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

            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new BeastHolder(view);
        }

        @Override
        public void onBindViewHolder(BeastHolder holder, int position) {
            Beast beast = beasts.get(position);
            holder.objectiveTextView.setText(beast.getObjective());
        }

        @Override
        public int getItemCount() {
            return beasts.size();
        }
    }
}
