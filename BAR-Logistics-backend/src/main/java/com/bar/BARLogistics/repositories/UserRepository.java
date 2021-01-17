package com.bar.BARLogistics.repositories;

import java.util.Optional;

import com.bar.BARLogistics.entities.Users;
import com.bar.BARLogistics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Users SET address.name = :address, first_name = :firstname, last_name = :lastname " +
            "WHERE id = :id")
    void editUserInformation(Long id, String address, String firstname, String lastname);

    @Transactional
    @Modifying
    @Query("UPDATE Users SET email = :email WHERE id = :id")
    void editUserEmail (Long id, String email);

    @Transactional
    @Modifying
    @Query("UPDATE Users SET username = :username WHERE id = :id")
    void editUsername (Long id, String username);



}
