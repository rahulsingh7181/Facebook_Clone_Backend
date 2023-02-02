package com.rahul.social.entities;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

/*
* Class for composite key of table Friends
* */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FriendshipKey implements Serializable {
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "receiver_id")
    private Long receiverId;
}
