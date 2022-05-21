package com.itmo.cats.coreModels.cat;

import java.io.Serializable;

public class FriendModel implements Serializable {
    private int _catId;

    private int _friendId;

    public FriendModel() {

    }

    public FriendModel(int catId, int friendId) {
        _catId = catId;
        _friendId = friendId;
    }

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
