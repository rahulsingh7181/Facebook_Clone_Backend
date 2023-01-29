package com.rahul.social.entities;

import java.io.Serializable;

/*
* Class for composite key of table Friends
* */
public class FriendshipKey implements Serializable {
    private Long senderId;
    private Long receiverId;
}
