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

    public FriendsFactory(Context context) {
        friends = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Friend f = new Friend();
            f.setName("Name " + i);
            friends.add(f);
        }
    }

    public List<Friend> getFriends() {
        return friends;
    }
}
