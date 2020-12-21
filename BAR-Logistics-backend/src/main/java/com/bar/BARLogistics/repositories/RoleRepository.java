package com.bar.BARLogistics.repositories;


import java.util.Optional;

import com.bar.BARLogistics.models.ERole;
import com.bar.BARLogistics.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
