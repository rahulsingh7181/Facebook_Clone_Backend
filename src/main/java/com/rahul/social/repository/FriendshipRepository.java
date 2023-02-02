package com.rahul.social.repository;

import com.rahul.social.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "SELECT f.* FROM friends f WHERE f.sender_id = ?1 AND f.receiver_id = ?2", nativeQuery = true)
    Friendship findByFriendshipId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

}
