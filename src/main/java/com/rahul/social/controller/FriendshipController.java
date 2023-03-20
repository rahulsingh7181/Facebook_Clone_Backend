package com.rahul.social.controller;

import com.rahul.social.dto.FriendshipDto;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.entities.FriendshipKey;
import com.rahul.social.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class FriendshipController {
    private final Logger logger = LoggerFactory.getLogger(FriendshipController.class);
    private final FriendshipService friendshipService;

    @PostMapping("/add-friend")
    public ResponseEntity<FriendshipDto> sendFriendRequest(@RequestParam("senderId") String  senderId,
                                                           @RequestParam("receiverId") String receiverId){
        logger.info("inside sendFriendRequest() method in FriendshipController class");
        FriendshipKey friendshipKey = FriendshipKey.builder()
                .senderId(Long.parseLong(senderId))
                .receiverId(Long.parseLong(receiverId))
                .build();
        FriendshipDto friendshipDto = FriendshipDto.builder().friendshipId(friendshipKey).build();
        return new ResponseEntity<FriendshipDto>(friendshipService.sendFriendRequest(friendshipDto), HttpStatus.OK);
    }

    @GetMapping("/getAllFriendRequest/{receiverId}")
    public ResponseEntity<List<UsersDto>> getAllFriendRequest(@PathVariable("receiverId") Long receiverId){
        logger.info("inside getAllFriendRequest() method in FriendshipController class receiverId : {}", receiverId);
        return new ResponseEntity<List<UsersDto>>(friendshipService.getAllFriendRequest(receiverId), HttpStatus.OK);
    }


    @PutMapping("/acceptFriendRequest")
    public ResponseEntity<String > acceptFriendRequest(@RequestParam("senderId") String  senderId,
                                                       @RequestParam("receiverId") String receiverId){
        logger.info("inside acceptFriendRequest() method in FriendshipController class ");
        FriendshipKey friendshipKey = FriendshipKey.builder()
                .senderId(Long.parseLong(senderId))
                .receiverId(Long.parseLong(receiverId))
                .build();
        FriendshipDto friendshipDto = FriendshipDto.builder().friendshipId(friendshipKey).build();
        FriendshipDto friendsDto = friendshipService.acceptFriendRequest(friendshipDto);
        logger.info("friendsDto :: {}",friendsDto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/getAllFriends/{receiverId}")
    public ResponseEntity<List<UsersDto>> getAllFriends(@PathVariable("receiverId") Long receiverId){
        logger.info("inside getAllFriends() method in FriendshipController class receiverId : {}", receiverId);
        return new ResponseEntity<List<UsersDto>>(friendshipService.getAllFriends(receiverId), HttpStatus.OK);
    }
}
