package com.itmo.cats.dtomodels.cat;

public class FriendRequest {
    private final int _catId;

    private final int _friendId;

    public FriendRequest(int catId, int friendId) {
        _catId = catId;
        _friendId = friendId;
    }

    public int getCatId() {
        return _catId;
    }

    public int getFriendId() {
        return _friendId;
    }
}
