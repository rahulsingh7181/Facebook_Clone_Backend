package com.rahul.social.services;

import com.rahul.social.dto.FriendshipDto;
import com.rahul.social.dto.UsersDto;

import java.util.List;

public interface FriendshipService {

    // send friend request
    FriendshipDto sendFriendRequest(FriendshipDto friendshipDto);
    // accept friend request
    FriendshipDto acceptFriendRequest(FriendshipDto friendshipDto);

    // get all friend requests
    List<UsersDto> getAllFriendRequest(Long receiverId);
    // get friends by userAccept Id
    List<FriendshipDto> getAllFriend(Long userId);
    // get all friends
    List<UsersDto> getAllFriends(Long receiverId);
    // block friend
    String blockFriend(FriendshipDto friendshipDto);
}
