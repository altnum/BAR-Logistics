package com.bar.BARLogistics.repositories;

import java.util.Optional;

import com.bar.BARLogistics.entities.Users;
import com.bar.BARLogistics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
