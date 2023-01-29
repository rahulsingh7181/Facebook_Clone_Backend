package com.rahul.social.entities;

import com.rahul.social.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(FriendshipKey.class)
@Table(name = "friends")
public class Friendship {

    @Id
    private Long senderId;
    @Id
    private Long receiverId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private UserStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date")
    private Date requestDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "accept_date")
    private Date acceptDate;
}
