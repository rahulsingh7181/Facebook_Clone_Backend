package com.rahul.social.entities;

import com.rahul.social.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "friends")
public class Friendship {

    @EmbeddedId
    @NotNull
    private FriendshipKey friendshipId;

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
