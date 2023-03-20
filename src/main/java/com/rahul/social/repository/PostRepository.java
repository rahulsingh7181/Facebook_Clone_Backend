package com.rahul.social.repository;

import com.rahul.social.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT DISTINCT p.*, u.first_name, u.last_name FROM post p " +
            "INNER JOIN users u on p.user_id = u.user_id " +
            "AND p.user_id = ?1 " +
            "OR p.user_id in (SELECT DISTINCT f.sender_id FROM friends f WHERE f.receiver_id = ?1) " +
            "OR p.user_id in (SELECT DISTINCT f.receiver_id FROM friends f WHERE f.sender_id = ?1) " +
            "GROUP BY p.post_id " +
            "ORDER BY p.post_date DESC;", nativeQuery = true)
    List<Post> findByUserId(@Param("receiverId") Long receiverId);
}
