package com.OSA.Bamboo.repository;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query(value = "DELETE FROM User u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);
}
