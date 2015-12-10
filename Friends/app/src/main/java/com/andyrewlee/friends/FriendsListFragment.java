package com.andyrewlee.friends;

import android.content.Intent;
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
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private class FriendHolder extends RecyclerView.ViewHolder {
        private Friend friend;
        private TextView nameTextView;

        public FriendHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.friend_name);
        }

        public void bindFriend(Friend friend) {
            this.friend = friend;
            nameTextView.setText(friend.getName());
        }
    }
}
