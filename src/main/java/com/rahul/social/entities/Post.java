package com.rahul.social.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_caption", nullable = false)
    private String caption;

    @Column(name = "post_image", nullable = false)
    private String postImage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "post_active")
    private Boolean isPostActive;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "userId",
            nullable = false,
            foreignKey = @ForeignKey(name = "post_user_fk"))
    private Users users;
}
