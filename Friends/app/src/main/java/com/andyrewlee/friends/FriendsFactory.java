package com.andyrewlee.friends;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 12/1/15.
 */
public class FriendsFactory {
    private static FriendsFactory friendsFactory;
    private List<Friend> friends;

    public static FriendsFactory get(Context context) {
        if (friendsFactory == null) {
            friendsFactory = new FriendsFactory(context);
        }
        return friendsFactory;
    }

    public Friend getFriend(UUID id) {
        for(Friend friend : friends) {
            if(friend.getId().equals(id)) {
                return friend;
            }
        }
        return null;
    }

    public FriendsFactory(Context context) {
        friends = new ArrayList<>();
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void addFriend(Friend newFriend) {
        friends.add(newFriend);
    }

    public void deleteFriend(UUID id) {
        for(int i = 0; i < friends.size(); i++) {
            if(friends.get(i).getId().equals(id)) {
                friends.remove(i);
            }
        }
    }
}
