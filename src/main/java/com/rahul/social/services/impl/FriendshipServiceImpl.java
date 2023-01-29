package com.rahul.social.services.impl;

import com.rahul.social.dto.FriendshipDto;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.entities.Friendship;
import com.rahul.social.entities.Users;
import com.rahul.social.enums.UserStatus;
import com.rahul.social.repository.FriendshipRepository;
import com.rahul.social.repository.UsersRepository;
import com.rahul.social.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

    private final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);
    private final FriendshipRepository friendshipRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Override
    public FriendshipDto sendFriendRequest(FriendshipDto friendshipDto) {
        logger.info("inside sendFriendRequest() method in FriendshipServiceImpl class");
        logger.info("friendsDto {}",friendshipDto);
        FriendshipDto friendshipDto1
                = FriendshipDto.builder()
                .senderId(friendshipDto.getSenderId())
                .receiverId(friendshipDto.getReceiverId())
                .status(UserStatus.PENDING)
                .requestDate(new Date())
                .acceptDate(null)
                .build();
        Friendship friendship = friendshipRepository.save(modelMapper.map(friendshipDto1, Friendship.class));
        logger.info("ReceiverId id  = {} and SenderId id = {}",friendship.getReceiverId(), friendship.getSenderId());
        return modelMapper.map(friendship, FriendshipDto.class);
    }

    @Override
    public FriendshipDto acceptFriendRequest() {
        logger.info("inside acceptFriendRequest() method in FriendshipServiceImpl class");
        FriendshipDto friendshipDto = new FriendshipDto();

        return null;
    }

    @Override
    public List<UsersDto> getAllFriendRequest(Long receiverId) {
        logger.info("inside getAllFriendRequest() method in FriendshipServiceImpl class receiverId : {}", receiverId);
        List<Users> users = usersRepository.findByUserId(receiverId, UserStatus.PENDING);
        logger.info("users : {}",users.size());
        return users.stream().map((user)-> modelMapper.map(user, UsersDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<FriendshipDto> getAllFriend(Long userId) {
        logger.info("inside getAllFriendRequest() method in FriendshipServiceImpl class");
        return null;
    }

    @Override
    public List<FriendshipDto> getAllFriends() {
        logger.info("inside getAllFriends() method in FriendshipServiceImpl class");
        return null;
    }

    @Override
    public String blockFriend(FriendshipDto friendshipDto) {
        logger.info("inside blockFriend() method in FriendshipServiceImpl class");
        return null;
    }
}
