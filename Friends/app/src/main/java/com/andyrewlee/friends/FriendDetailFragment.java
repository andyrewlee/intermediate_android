package com.andyrewlee.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by dev1 on 11/30/15.
 */
// make sure we are importing the support Fragment
public class FriendDetailFragment extends Fragment {

    private static final String ARG_FRIEND_ID = "com.codingdojo.friends.student_id";

    private Friend friend;
    private EditText friendNameEditText;
    private Button deleteFriendButton;

    public static FriendDetailFragment newInstance(UUID studentId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FRIEND_ID, studentId);

        FriendDetailFragment fragment = new FriendDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // this is not where we inflate our views and add event listeners
    // we will be using this method to load up some initial data later
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FriendsFactory friendsFactory = FriendsFactory.get(getActivity());
        UUID friendId = (UUID) getArguments().getSerializable(ARG_FRIEND_ID);

        friend = friendsFactory.getFriend(friendId);
    }

    // this is where we inflate our views and add event listeners
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // we have not created this view yet
        View view = inflater.inflate(R.layout.fragment_friend_detail, container, false);

        friendNameEditText = (EditText) view.findViewById(R.id.friend_name);
        friendNameEditText.setText(friend.getName());

        friendNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                friend.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        deleteFriendButton = (Button) view.findViewById(R.id.delete_friend_button);

        deleteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsFactory friendsFactory = FriendsFactory.get(getActivity());
                friendsFactory.deleteFriend(friend.getId());
                getActivity().finish();
            }
        });

        return view;
    }
}
