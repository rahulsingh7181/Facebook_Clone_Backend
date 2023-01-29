package com.rahul.social.repository;

import com.rahul.social.entities.Users;
import com.rahul.social.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmailAddress(String emailAddress);
    List<Users> findByMobileNumber(String mobileNumber);
    // search users
    @Query("SELECT u FROM Users u WHERE " +
            "u.firstName LIKE CONCAT('%', :searchString, '%')" +
            " OR u.lastName LIKE CONCAT('%', :searchString, '%')" +
            " OR u.emailAddress LIKE CONCAT('%', :searchString, '%')")
    List<Users> searchUsers(String searchString);

    @Query(value = "SELECT users.* FROM users " +
            "JOIN friends ON users.user_id = friends.sender_id " +
            "WHERE friends.receiver_id = ?1 " +
            "AND friends.status = ?2", nativeQuery = true)
    List<Users> findByUserId(@Param("receiverId") Long receiverId, @Param("status") UserStatus status);
}
