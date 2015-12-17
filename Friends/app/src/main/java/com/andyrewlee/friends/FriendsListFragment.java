package com.andyrewlee.friends;

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
 * Created by dev1 on 12/1/15.
 */
public class FriendsListFragment extends Fragment {
    private RecyclerView friendsListRecyclerView;
    private FriendsAdapter adapter;

    private void updateUI() {
        FriendsFactory friendsFactory = FriendsFactory.get(getActivity());
        List<Friend> friends = friendsFactory.getFriends();

        if(adapter == null) {
            adapter = new FriendsAdapter(friends);
            friendsListRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_friends_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_friend:
                Friend friend = new Friend();
                FriendsFactory.get(getActivity()).addFriend(friend);
                Intent intent = FriendDetailActivity.newIntent(getActivity(), friend.getId());
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // we have not created this view yet
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);

        friendsListRecyclerView = (RecyclerView) view.findViewById(R.id.student_list);
        friendsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private class FriendsAdapter extends RecyclerView.Adapter<FriendHolder> {
        private List<Friend> friends;

        public FriendsAdapter(List<Friend> friends) {
            this.friends = friends;
        }

        @Override
        public int getItemCount() {
            return friends.size();
        }

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View itemView = layoutInflater.inflate(R.layout.item_view_friend, parent, false);
            return new FriendHolder(itemView);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            Friend friend = friends.get(position);
            holder.bindFriend(friend);
        }
    }

    private class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Friend friend;
        private TextView nameTextView;

        public FriendHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameTextView = (TextView) itemView.findViewById(R.id.friend_name);
        }

        public void bindFriend(Friend friend) {
            this.friend = friend;
            nameTextView.setText(friend.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = FriendDetailActivity.newIntent(getActivity(), friend.getId());
            startActivity(intent);
        }
    }
}
