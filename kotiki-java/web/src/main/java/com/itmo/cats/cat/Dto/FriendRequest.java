package com.itmo.cats.cat.Dto;

public class FriendRequest {
    private int _catId;

    private int _friendId;

    public int getCatId() {
        return _catId;
    }

    public void setCatId(int catId) {
        _catId = catId;
    }

    public int getFriendId() {
        return _friendId;
    }

    public void setFriendId(int friendId) {
        _friendId = friendId;
    }
}
